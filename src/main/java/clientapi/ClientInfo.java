/*
 * Copyright 2017 ImpactDevelopment
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

/**
 * Representation of the {@code client.json} format.
 *
 * @see Client
 * @author Brady
 * @since 1/24/2017 12:00 PM
 */
public final class ClientInfo {

    /**
     * Client name
     */
    private String name;

    /**
     * List of Client Authors
     */
    private String[] authors;

    /**
     * Client Unique ID
     */
    private String id;

    /**
     * Client Build
     */
    private double build;

    /**
     * Release Type
     */
    private ReleaseType type;

    /**
     * The Main Class
     */
    private String main;

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
        return authors;
    }

    /**
     * @return The Client Unique ID
     */
    public String getId() {
        return id;
    }

    /**
     * @return The Client Build
     */
    public double getBuild() {
        return build;
    }

    /**
     * @return The Release Type
     */
    public ReleaseType getType() {
        return type;
    }

    /**
     * @return The Main Class
     */
    public String getMain() {
        return main;
    }

    public enum ReleaseType {
        ALPHA, BETA, SNAPSHOT, RELEASE
    }
}
