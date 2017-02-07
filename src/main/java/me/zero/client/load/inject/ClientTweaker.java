package me.zero.client.load.inject;

import me.zero.client.load.ClientAPI;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Calls the transformer and loads the target Client
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public final class ClientTweaker implements ITweaker {

    /**
     * The Game Launch Arguments
     */
    private List<String> args = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.args.addAll(args);

        if (gameDir != null) {
            this.args.add("--gameDir");
            this.args.add(gameDir.getAbsolutePath());
        }

        if (assetsDir != null) {
            this.args.add("--assetsDir");
            this.args.add(assetsDir.getAbsolutePath());
        }

        if (profile != null) {
            this.args.add("--version");
            this.args.add(profile);
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        ClientAPI.getAPI().init(this);
        classLoader.registerTransformer(ClientTransformer.class.getCanonicalName());
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return this.args.toArray(new String[this.args.size()]);
    }

    /**
     * @since 1.0
     *
     * @return The Game Launch Arguments
     */
    public List<String> getArguments() {
        return this.args;
    }
}
