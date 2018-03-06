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

import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains some constants that are used throughout the API.
 *
 * @author Brady
 * @since 5/27/2017 9:52 AM
 */
public final class ClientAPI {

    /**
     * Prevent instantiation
     */
    private ClientAPI() {}

    /**
     * Instance of the API event bus. All default events are passed through this event bus.
     */
    public static final EventBus EVENT_BUS = new EventManager();

    /**
     * Instance of the API logger.
     */
    public static final Logger LOGGER = LogManager.getLogger("ClientAPI");

    /**
     * Whether or not the game is running with Minecraft Forge
     */
    public static final boolean RUNNING_IN_FORGE = findMinecraftForgeClass() != null;

    /**
     * Tries to find the {@code MinecraftForge} class. Will return
     * {@code null} if the class is not found, indicating that Minecraft
     * Forge is not present in this environment.
     *
     * @return The {@code MinecraftForge} class. {@code null} if not found.
     */
    private static Class<?> findMinecraftForgeClass() {
        try {
            return Class.forName("net.minecraftforge.common.MinecraftForge");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
