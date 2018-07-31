/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.load;

import clientapi.ClientAPI;
import clientapi.load.config.ClientConfiguration;
import clientapi.load.config.JsonConfiguration;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.tools.obfuscation.mcp.ObfuscationServiceMCP;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Calls the transformer and loads the target Client
 *
 * @author Brady
 * @since 1/20/2017 12:00 PM
 */
public class ClientTweaker implements ITweaker {

    /**
     * The Game Launch Arguments
     */
    List<String> args;

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        (this.args = new ArrayList<>()).addAll(args);
        addArg("gameDir", gameDir);
        addArg("assetsDir", assetsDir);
        addArg("version", profile);
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        ClientAPI.LOGGER.log(Level.INFO, "Injecting into ClassLoader");

        MixinBootstrap.init();
        ClientAPI.LOGGER.log(Level.INFO, "Initialized Mixin bootstrap");

        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext(ObfuscationServiceMCP.NOTCH);
        ClientAPI.LOGGER.log(Level.INFO, "Setup Mixin Environment");

        // Load byteode transformers
        classLoader.registerTransformer(ClientTransformer.class.getName());

        ClientAPI.LOGGER.log(Level.INFO, "Registered Bytecode Transformes");

        // Load mixin configs
        loadMixinConfig("mixins.capi.json");

        // Load Client defined mixin configs
        for (String config : findClientConfig().getMixins())
            loadMixinConfig(config);

        ClientAPI.LOGGER.log(Level.INFO, "Loaded Mixin Configurations");
    }

    @Override
    public final String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public final String[] getLaunchArguments() {
        return this.args.toArray(new String[this.args.size()]);
    }

    private ClientConfiguration findClientConfig() {
        InputStream stream;
        if ((stream = this.getClass().getResourceAsStream("/client.json")) == null)
            throw new ClientInitException("Unable to locate Client Configuration");

        return JsonConfiguration.loadConfiguration(stream, ClientConfiguration.class);
    }

    private void loadMixinConfig(String config) {
        // Verify the existence of the specified configuration file
        if (this.getClass().getResourceAsStream("/" + config) == null)
            throw new ClientInitException("Unable to locate mixin configuration %s", config);

        Mixins.addConfiguration(config);
    }

    private void addArg(String label, File file) {
        if (file != null)
            addArg(label, file.getAbsolutePath());
    }

    private void addArg(String label, String value) {
        if (!args.contains("--" + label) && value != null) {
            this.args.add("--" + label);
            this.args.add(value);
        }
    }
}
