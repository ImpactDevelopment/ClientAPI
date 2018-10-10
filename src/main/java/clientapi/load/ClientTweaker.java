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
import clientapi.config.ClientConfiguration;
import clientapi.config.JsonConfiguration;
import clientapi.load.argument.Argument;
import clientapi.load.argument.Arguments;
import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
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
 * @since 1/20/2017
 */
public class ClientTweaker implements ITweaker {

    /**
     * The Game Launch Arguments
     */
    private List<String> args;

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

        String obfuscation = ObfuscationServiceMCP.NOTCH;
        if (Launch.classLoader.getTransformers().stream().noneMatch(t -> t.getClass().getName().contains("fml"))) {
            obfuscation = ObfuscationServiceMCP.SEARGE;
        }

        MixinEnvironment.getDefaultEnvironment().setObfuscationContext(obfuscation);

        ClientAPI.LOGGER.log(Level.INFO, "Setup Mixin Environment");

        ClientConfiguration config = findClientConfig();

        // Load bytecode transformers
        classLoader.registerTransformer(ClientTransformer.class.getName());
        ClientTransformer.getInstance().registerAll(config.getTransformers());

        ClientAPI.LOGGER.log(Level.INFO, "Registered Bytecode Transformes");

        // Load mixin configs
        loadMixinConfig("mixins.capi.json");

        // Load Client defined mixin configs
        for (String mixin : config.getMixins())
            loadMixinConfig(mixin);

        ClientAPI.LOGGER.log(Level.INFO, "Loaded Mixin Configurations");
    }

    @Override
    public final String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    @SuppressWarnings("unchecked")
    public final String[] getLaunchArguments() {
        // Parse the arguments that we are able to pass to the game
        List<Argument> parsed = Arguments.parse(this.args);

        // Parse the arguments that are already being passed to the game
        List<Argument> existing = Arguments.parse((List<String>) Launch.blackboard.get("ArgumentList"));

        // Remove any arguments that match already existing ones
        parsed.removeIf(argument -> existing.stream().anyMatch(a -> a.matches(argument)));

        // Join back the filtered arguments and pass those to the game
        return Arguments.join(parsed).toArray(new String[0]);
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
