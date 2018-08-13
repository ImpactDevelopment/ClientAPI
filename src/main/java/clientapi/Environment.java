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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brady
 * @since 8/12/2018
 */
public final class Environment {

    private Environment() {}

    /**
     * Maps class names to whether or not they are loaded.
     */
    private static final Map<String, Boolean> CLASS_LOAD_CACHE = new HashMap<>();

    /**
     * @return Whether or not Minecraft Forge is detected
     */
    public static boolean isRunningForge() {
        return isClassLoaded("net.minecraftforge.common.MinecraftForge");
    }

    /**
     * @return Whether or not LiteLoader is detected
     */
    public static boolean isRunningLiteloader() {
        return isClassLoaded("com.mumfrey.liteloader.LiteMod");
    }

    /**
     * @return Whether or not OptiFine is detected
     */
    public static boolean isRunningOptiFine() {
        return isClassLoaded("Config");
    }

    /**
     * Checks if the specified class, by name, is loaded
     *
     * @param name The class name
     * @return Whether or not the class is loaded
     */
    private static boolean isClassLoaded(String name) {
        return CLASS_LOAD_CACHE.computeIfAbsent(name, c -> {
            try {
                Class.forName(c);
                return true;
            } catch (ClassNotFoundException e) {
                return false;
            }
        });
    }
}
