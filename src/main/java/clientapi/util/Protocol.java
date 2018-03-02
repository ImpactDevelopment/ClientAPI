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

package clientapi.util;

/**
 * List of official release version protocols
 * after the netty rewrite in 1.7
 *
 * @author Brady
 * @since 3/6/2017 12:00 PM
 */
public enum Protocol {

    ProtocolVersion4(4, "1.7.2", "1.7.4", "1.7.5"),
    ProtocolVersion5(5, "1.7.6", "1.7.7", "1.7.8", "1.7.9", "1.7.10"),
    ProtocolVersion47(47, "1.8", "1.8.1", "1.8.2", "1.8.3", "1.8.4", "1.8.5", "1.8.6", "1.8.7", "1.8.8", "1.8.9"),
    ProtocolVersion107(107, "1.9"),
    ProtocolVersion108(108, "1.9.1"),
    ProtocolVersion109(109, "1.9.2"),
    ProtocolVersion110(110, "1.9.3", "1.9.4"),
    ProtocolVersion210(210, "1.10", "1.10.1", "1.10.2"),
    ProtocolVersion315(315, "1.11"),
    ProtocolVersion316(316, "1.11.1", "1.11.2"),
    ProtocolVersion335(335, "1.12");

    /**
     * The Protocol ID
     */
    private final int id;

    /**
     * Array of supported game versions for this protocol version
     */
    private final String[] versions;

    Protocol(int id, String... versions) {
        this.id = id;
        this.versions = versions;
    }

    /**
     * @return The Protocol ID
     */
    public final int getId() {
        return this.id;
    }

    /**
     * @return Array of supported game versions for this protocol version
     */
    public final String[] getVersions() {
        return this.versions;
    }
}
