package me.zero.client.load;

import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Calls the transformer and loads the target Client
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/20/2017 12:00 PM
 */
public final class ClientTweaker implements ITweaker {

    /**
     * The Game Launch Arguments
     */
    private List<String> args = new ArrayList<>();

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
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.capi.json");

        // Optional mixin configuration, added by client developers
        String mixin = "mixins.client.json";
        if (this.getClass().getResourceAsStream("/" + mixin) != null)
            Mixins.addConfiguration(mixin);

        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
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
