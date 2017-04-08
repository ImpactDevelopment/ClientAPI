package me.zero.client.api.value;

/**
 * Properties are similar to values but are not limited to Fields.
 * Used in Nodes to handle unique properties of the Node.
 *
 * @since 1.0
 *
 * Created by Brady on 2/25/2017.
 */
public final class Property {

    /**
     * The label of this property
     */
    private String label;

    /**
     * The value of this property
     */
    private Object value;

    public Property(String label) {
        this.label = label;
    }

    /**
     * @since 1.0
     *
     * @return The label of this property
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * @since 1.0
     *
     * @return The value of this property
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue() {
        return (T) this.value;
    }

    /**
     * @since 1.0
     *
     * @return The value of this property as a string
     */
    public String getString() {
        return value.toString();
    }

    /**
     * Sets the value of this property to the specified object
     *
     * @since 1.0
     *
     * @param value New value
     */
    public void setValue(Object value) {
        this.value = value;
    }
}
