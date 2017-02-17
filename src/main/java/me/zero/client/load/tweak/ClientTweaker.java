package me.zero.client.load.tweak;

import me.zero.client.api.ClientInfo;
import me.zero.client.api.exception.UnexpectedOutcomeException;
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
        // Exclude Transformers so that this doesn't break!
        Launch.classLoader.addClassLoaderExclusion("me.zero.client.load.transformer");

        ClientInfo info = ClientLoader.getInfo();
        if (info == null)
            throw new UnexpectedOutcomeException("Unable to create LaunchClassLoader exclusions. ClientInfo not found.");

        if (info.getTransformers().length() > 0)
            Launch.classLoader.addClassLoaderExclusion(info.getTransformers());
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
