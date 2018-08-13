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

package clientapi.config;

import clientapi.util.io.StreamReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;

/**
 * Base for a Json based Configuration file.
 *
 * @author Brady
 * @since 7/31/2018
 */
public interface JsonConfiguration {

    Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Loads a configuration
     *
     * @param stream A stream for the configuration file
     * @param configurationClass The class of the configuration type
     * @param <T> The configuration type
     * @return The instantiated configuration
     */
    static <T extends JsonConfiguration> T loadConfiguration(InputStream stream, Class<T> configurationClass) {
        return loadConfiguration(new StreamReader(stream).all(), configurationClass);
    }

    /**
     * Loads a configuration
     *
     * @param configuration The configuration raw json
     * @param configurationClass The class of the configuration type
     * @param <T> The configuration type
     * @return The instantiated configuration
     */
    static <T extends JsonConfiguration> T loadConfiguration(String configuration, Class<T> configurationClass) {
        return GSON.fromJson(configuration, configurationClass);
    }
}
