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

package clientapi;

import clientapi.config.ClientConfiguration;
import clientapi.config.JsonConfiguration;
import clientapi.event.ClientAPIEventManager;
import clientapi.event.handle.KeybindEventHandler;
import clientapi.load.ClientInitException;
import clientapi.util.render.gl.GLUtils;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Contains some constants that are used throughout the API.
 *
 * @author Brady
 * @since 5/27/2017
 */
public final class ClientAPI {

    /**
     * Prevent instantiation
     */
    private ClientAPI() {}

    /**
     * Instance of the API event bus. All default events are passed through this event bus.
     */
    public static final ClientAPIEventManager EVENT_BUS = new ClientAPIEventManager();

    /**
     * Instance of the API logger.
     */
    public static final Logger LOGGER = LogManager.getLogger("ClientAPI");

    /**
     * Called after the game has initialized. This method is responsible
     * for initializing any core Client API classes and the discovered
     * Client API mod.
     *
     * @see Minecraft#init()
     */
    public static void start() {
        InputStream stream = ClientAPI.class.getResourceAsStream("/client.json");
        if (stream == null) {
            throw new ClientInitException("Unable to locate Client Configuration");
        }

        // Construct a ClientConfiguration object from the client json using GSON
        ClientConfiguration clientConfig = JsonConfiguration.loadConfiguration(stream, ClientConfiguration.class);
        if (clientConfig == null) {
            throw new ClientInitException("Unable to create Client Configuration from client.json");
        }

        // Attempt to instantiate the main class from the client configuration
        Client client;
        try {
            Class<?> clientClass = Class.forName(clientConfig.getMainClass());
            if (clientClass == null) {
                throw new ClientInitException("Unable to find the main class (%s)", clientConfig.getMainClass());
            }

            if (!clientClass.getSuperclass().equals(Client.class)) {
                throw new ClientInitException("Main class does not inheit clientapi.Client");
            }

            Constructor<?> constructor = clientClass.getConstructor(ClientConfiguration.class);

            client = (Client) constructor.newInstance(clientConfig);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ClientInitException("Unable to instantiate main client class", e);
        } catch (ClassNotFoundException e) {
            throw new ClientInitException("Unable to find client class");
        } catch (NoSuchMethodException e) {
            throw new ClientInitException("Unable to find a suitable main class constructor");
        }

        // Init GLUtils
        GLUtils.init();

        // Init client
        client.init();

        ClientAPI.EVENT_BUS.subscribe(KeybindEventHandler.INSTANCE);
    }
}
