package me.zero.client.load.inject;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public class ClientTweaker implements ITweaker {

    private List<String> args = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.args.addAll(args);

        this.args.add("--gameDir");
        this.args.add(gameDir.getAbsolutePath());

        this.args.add("--assetsDir");
        this.args.add(assetsDir.getAbsolutePath());

        this.args.add("--version");
        this.args.add(profile);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        classLoader.registerTransformer("me.zero.client.load.inject.ClientTransformer");
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return this.args.toArray(new String[this.args.size()]);
    }
}
