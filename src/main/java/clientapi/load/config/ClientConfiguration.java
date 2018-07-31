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

package clientapi.load.config;

import clientapi.Client;

/**
 * Representation of the {@code client.json} format.
 *
 * @see Client
 *
 * @author Brady
 * @since 1/24/2017 12:00 PM
 */
public final class ClientConfiguration implements JsonConfiguration {

    /**
     * The name of the ClientAPI mod
     */
    private String name;

    /**
     * List of the names of the Client authors
     */
    private String[] authors;

    /**
     * A unique ID for the client
     */
    private String id;

    /**
     * The version of the Client
     */
    private String version;

    /**
     * The classpath of the main class extending {@link Client}
     */
    private String mainClass;

    /**
     * An array of the defined mixin configurations
     */
    private String[] mixins;

    /**
     * @return The Client Name
     */
    public String getName() {
        return name;
    }

    /**
     * @return The authors of the Client
     */
    public String[] getAuthors() {
        // If the authors were left unspecified just default it to none
        if (this.authors == null)
            this.authors = new String[0];

        return this.authors;
    }

    /**
     * Returns a unique ID for the Client. If left unspecified,
     * it will default to the classpath of the main {@link Client} class.
     *
     * @return A unique ID for the client
     */
    public String getId() {
        if (this.id == null)
            this.id = this.mainClass;

        return this.id;
    }

    /**
     * @return The version of the Client
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Returns the classpath of the main {@link Client} class
     * that will be instantiated on the main game initialization
     * after the transformating loading stage.
     *
     * @return The classpath of the main class extending {@link Client}
     */
    public String getMainClass() {
        return this.mainClass;
    }

    /**
     * Returns an array of the defined mixin configurations that
     * are used in the transformation loading stage. (Prior to
     * Minecraft being launched). If left undefined, an empty
     * String array will be returned.
     *
     * @return An array of the defined mixin configurations
     */
    public String[] getMixins() {
        // If the custom mixin configurations were left unspecified just default it to none
        if (this.mixins == null)
            this.mixins = new String[0];

        return this.mixins;
    }
}
