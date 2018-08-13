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

import clientapi.util.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Gives an object access to have tags that give identity to that object.
 *
 * @see Tag
 *
 * @author Brady
 * @since 11/1/2017
 */
public interface Taggable {

    /**
     * Adds a tag to this object. Tags are only added if
     * a tag with the same ID has not already been added.
     *
     * @param tag The tag to add
     */
    void addTag(Tag tag);

    /**
     * Removes a tag by the specified tag ID
     *
     * @param id The ID of the tag to remove
     */
    void removeTag(String id);

    /**
     * Removes a tag with a matching ID to the specified ID
     *
     * @param id The ID
     */
    default void removeTag(Identifiable id) {
        removeTag(id.getID());
    }

    /**
     * Checks whether or not this object has a tag with an ID
     * that matches the specified ID.
     *
     * @param id The tag being searched for
     * @return Whether or not this object has a tag with the specified ID
     */
    boolean hasTag(String id);

    /**
     * Checks whether or not this object has a tag with an ID
     * that matches the ID of the specified {@code Identifiable}.
     *
     * @param id The {@code Identifiable} being matched
     * @return Whether or not this object has a tag with the specified ID
     */
    default boolean hasTag(Identifiable id) {
        return this.hasTag(id.getID());
    }

    /**
     * Gets a tag with an ID matching the specified ID.
     *
     * @param id The ID of the target tag
     * @return The tag with the specified ID
     */
    Optional<Tag> getTag(String id);

    /**
     * Gets a tag with an ID matching the ID of the specified {@code Identifiable}.
     *
     * @param id The {@code Identifiable} being matched
     * @return The tag with a matching ID
     */
    default Optional<Tag> getTag(Identifiable id) {
        return this.getTag(id.getID());
    }

    /**
     * Gets a tag with an ID matching that of the specified
     * {@code Identifiable}'s ID.
     *
     * @param id The {@code Identifiable} being matched
     */

    /**
     * @return A list of this object's tags
     */
    List<Tag> getTags();
}
