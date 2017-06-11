/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.load;

import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Calls the transformer and loads the target Client
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

        // Initialize the Mixin Bootstrap
        MixinBootstrap.init();

        // Load the ClientAPI and Wrapper mixins
        Mixins.addConfiguration("mixins.capi.json");
        Mixins.addConfiguration("mixins.wrapper.capi.json");

        // Optional mixin configuration, added by client developers
        String mixin = "mixins.client.json";
        if (this.getClass().getResourceAsStream("/" + mixin) != null)
            Mixins.addConfiguration(mixin);

        // Ensure that the mixins are only run on client side
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
