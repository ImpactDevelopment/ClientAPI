package me.zero.client.api.value;

/**
 * Simple interface for Values
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
 */
interface IValue<T> {

    /**
     * @return The Value of this Object
     */
    T getValue();

    /**
     * Sets the Value of this Object
     *
     * @param value The new value
     */
    void setValue(T value);
}
