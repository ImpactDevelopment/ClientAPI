package me.zero.client.api.value;

/**
 * Properties are similar to values but are not limited to Fields.
 * Used in Nodes to handle unique properties of the Node.
 *
 * @author Brady
 * @since 2/25/2017 12:00 PM
 */
public final class Property {

    /**
     * The label of this property
     */
    private final String label;

    /**
     * The value of this property
     */
    private Object value;

    public Property(String label) {
        this.label = label;
    }

    /**
     * @return The label of this property
     */
    public final String getLabel() {
        return this.label;
    }

    /**
     * @return The value of this property
     */
    @SuppressWarnings("unchecked")
    public final <T> T getValue() {
        return (T) this.value;
    }

    /**
     * @return The value of this property as a string
     */
    public final String getString() {
        return value.toString();
    }

    /**
     * Sets the value of this property to the specified object
     *
     * @param value New value
     */
    public final void setValue(Object value) {
        this.value = value;
    }
}
