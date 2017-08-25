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
 * Used to track the version of the API. An API version consists of a Major,
 * Minor, and Patch version.
 *
 * @author Brady
 * @since 8/16/2017 7:11 PM
 */
public final class Version {

    private Version() {}

    // Incremented when a new version of Minecraft is released, never reset
    public static final int MAJOR = 3;

    // Incremented when API-breaking changes are made, reset when major version
    // is modified
    public static final int MINOR = 0;

    // Incremented every release, reset when the minor version is modified
    public static final int PATCH = 0;

    public static String getVersion() {
        return String.format("%d.%d.%d", MAJOR, MINOR, PATCH);
    }
}
