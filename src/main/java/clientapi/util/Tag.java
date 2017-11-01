package clientapi.util;

import clientapi.util.interfaces.Identifiable;

/**
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

    @SuppressWarnings("unchecked")
    public final <U> U getValue() {
        return (U) this.value;
    }

    @SuppressWarnings("unchecked")
    public final <U> void setValue(U value) {
        this.value = (T) value;
    }

    @Override
    public final String getId() {
        return this.id;
    }
}
