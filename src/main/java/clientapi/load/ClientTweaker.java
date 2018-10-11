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
import clientapi.load.transform.impl.ValueAccessorTransformer;
import io.github.impactdevelopment.simpletweaker.SimpleTweaker;
import io.github.impactdevelopment.simpletweaker.transform.SimpleTransformer;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.tools.obfuscation.mcp.ObfuscationServiceMCP;

import java.io.InputStream;
import java.util.List;

/**
 * Calls the transformer and loads the target Client
 *
 * @author Brady
 * @since 1/20/2017
 */
public final class ClientTweaker extends SimpleTweaker {

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        super.injectIntoClassLoader(classLoader);

        ClientAPI.LOGGER.info("Injecting into ClassLoader");

        this.setupMixin();

        ClientConfiguration config = findClientConfig();

        this.setupTransformers(config);

        // Load mixin configs
        loadMixinConfig("mixins.capi.json");

        // Load Client defined mixin configs
        for (String mixin : config.getMixins())
            loadMixinConfig(mixin);

        ClientAPI.LOGGER.info("Loaded Mixin Configurations");
    }

    @Override
    public final String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @SuppressWarnings("unchecked")
    private void setupMixin() {
        MixinBootstrap.init();
        ClientAPI.LOGGER.info("Initialized Mixin bootstrap");

        // Find all of the other tweakers that are being loaded
        List<String> tweakClasses = (List<String>) Launch.blackboard.get("TweakClasses");

        // Default to NOTCH obfuscation context
        String obfuscation = ObfuscationServiceMCP.NOTCH;

        // If there are any tweak classes that contain "FMLTweaker", then set the obfuscation context to SEARGE
        if (tweakClasses.stream().anyMatch(s -> s.contains("FMLTweaker"))) {
            obfuscation = ObfuscationServiceMCP.SEARGE;
            ClientAPI.LOGGER.info("Discovered FML! Switching to SEARGE mappings.");
        }

        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext(obfuscation);
        ClientAPI.LOGGER.info("Setup Mixin Environment");
    }

    private void setupTransformers(ClientConfiguration config) {
        SimpleTransformer transformer = SimpleTransformer.getInstance();

        if (!Boolean.valueOf(System.getProperty("clientapi.load.devMode", "false")))
            transformer.registerAll(new ValueAccessorTransformer());

        transformer.registerAll(config.getTransformers());

        ClientAPI.LOGGER.info("Registered Bytecode Transformes");
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
}
