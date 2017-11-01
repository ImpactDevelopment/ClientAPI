package clientapi.util;

import clientapi.util.interfaces.Identifiable;
import clientapi.util.interfaces.Taggable;

/**
 * A tag. Refer to {@code Taggable} for usage.
 *
 * @see Taggable
 *
 * @author Brady
 * @since 11/1/2017 2:29 PM
 */
public final class Tag<T> implements Identifiable {

    /**
     * The value of the tag
     */
    private T value;

    /**
     * The tag's id. Should be the same across tags
     * that represent the same property.
     */
    private final String id;

    public Tag(String id) {
        this(null, id);
    }

    public Tag(T initialValue, String id) {
        this.value = initialValue;
        this.id = id;
    }

    /**
     * Gets the value of this tag.
     *
     * @param <U> The type to cast the value to
     * @return The value of this tag.
     */
    @SuppressWarnings("unchecked")
    public final <U> U getValue() {
        return (U) this.value;
    }

    /**
     * Sets the value of this tag.
     *
     * @param value The new value
     * @param <U> The type to cast to the value type
     */
    @SuppressWarnings("unchecked")
    public final <U> void setValue(U value) {
        this.value = (T) value;
    }

    @Override
    public final String getId() {
        return this.id;
    }
}
