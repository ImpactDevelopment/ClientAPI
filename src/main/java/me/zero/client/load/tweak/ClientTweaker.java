package me.zero.client.load.tweak;

import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import me.zero.client.load.discover.ClientLoader;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
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

    public ClientTweaker() {
        // Add all of this so that we don't get any
        // errors that actually drove me insane for
        // the past hour and a half hahahah kill me
        Launch.classLoader.addClassLoaderExclusion("me.zero.client.load.tweak");
        Launch.classLoader.addClassLoaderExclusion("me.zero.client.load.transformer");
        Launch.classLoader.addClassLoaderExclusion("me.zero.client.load.transformer.defaults");
        Launch.classLoader.addClassLoaderExclusion("me.zero.client.load.transformer.hook");
        Launch.classLoader.addClassLoaderExclusion("me.zero.client.load.transformer.reference");
        Launch.classLoader.addClassLoaderExclusion("me.zero.client.load.transformer.reference.obfuscation");
        Launch.classLoader.addClassLoaderExclusion("me.zero.client.load.discover");
        Launch.classLoader.addClassLoaderExclusion("org.objectweb.asm.");
    }

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.args.addAll(args);

        addArg("gameDir", gameDir);
        addArg("assetsDir", assetsDir);
        addArg("version", profile);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        Logger.instance.log(Level.INFO, "Injecting into ClassLoader");
        classLoader.registerTransformer("me.zero.client.load.tweak.ClientTransformer");
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return this.args.toArray(new String[this.args.size()]);
    }

    private void addArg(String label, File file) {
        if (file != null)
            addArg(label, file.getAbsolutePath());
    }

    private void addArg(String label, String value) {
        if (value != null) {
            this.args.add("--" + label);
            this.args.add(value);
        }
    }
}
