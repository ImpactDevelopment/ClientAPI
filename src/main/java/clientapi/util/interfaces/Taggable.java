package clientapi.util.interfaces;

import clientapi.util.Tag;

import java.util.List;

/**
 * Gives an object access to have tags that give identity to that object.
 *
 * @see Tag
 *
 * @author Brady
 * @since 11/1/2017 1:39 PM
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
        removeTag(id.getId());
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
        return this.hasTag(id.getId());
    }

    /**
     * @return A list of this object's tags
     */
    List<Tag> getTags();
}
