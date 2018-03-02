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

package clientapi.util.interfaces;

/**
 * @author Brady
 * @since 9/7/2017 9:35 PM
 */
public interface Identifiable {

    /**
     * @return The ID of this {@code Identifiable}
     */
    String getID();

    /**
     * Checks if the ID of this {@code Identifiable} and
     * the ID of another {@code Identifiable} match each other.
     *
     * @param id The other {@code Identifiable}
     * @return Whether or not they match
     */
    default boolean matches(Identifiable id) {
        return this.getID().equals(id.getID());
    }
}
