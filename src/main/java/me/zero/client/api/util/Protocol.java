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

package me.zero.client.api.util;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * List of protocols
 *
 * @author Brady
 * @since 3/6/2017 12:00 PM
 */
public final class Protocol {

    private static ImmutableList<Protocol> protocols = new ImmutableList.Builder<Protocol>()
            .add(build(316, "1.11.x", "1.11", "1.11.2"))
            .add(build(315, "1.11"))
            .add(build(210, "1.10.x", "1.10", "1.10.1", "1.10.2"))
            .add(build(110, "1.9.3", "1.9.3", "1.9.4"))
            .add(build(109, "1.9.2", "1.9.2"))
            .add(build(108, "1.9.1"))
            .add(build(107, "1.9"))
            .add(build(47, "1.8.x", "1.8", "1.8.1", "1.8.2", "1.8.3", "1.8.4", "1.8.5", "1.8.6", "1.8.7", "1.8.8", "1.8.9"))
            .add(build(5, "1.7.10", "1.7.6", "1.7.7", "1.7.8", "1.7.9", "1.7.10"))
            .add(build(4, "1.7.2", "1.7.2", "1.7.4", "1.7.5"))
            .build();

    /**
     * Protocol ID
     */
    private final int protocol;

    /**
     * Protocol display name
     */
    private final String name;

    /**
     * Supported versions
     */
    private final String[] versions;

    private Protocol(int protocol, String name, String... versions) {
        this.protocol = protocol;
        this.name = name;
        this.versions = versions;
    }

    /**
     * @return The protocol ID
     */
    public final int getProtocol() {
        return this.protocol;
    }

    /**
     * @return The protocol display name
     */
    public final String getName() {
        return this.name;
    }

    /**
     * @return The supported version IDs
     */
    public final String[] getVersions() {
        return this.versions;
    }

    /**
     * Creates a Protocol object from its required parameters
     *
     * @param protocol The protocol id
     * @param name The name of the protocol
     * @param versions An array of supported versions
     * @return The built object
     */
    private static Protocol build(int protocol, String name, String... versions) {
        return new Protocol(protocol, name, versions);
    }

    /**
     * @return All of the registered protocols
     */
    public static List<Protocol> getProtocols() {
        return protocols;
    }
}
