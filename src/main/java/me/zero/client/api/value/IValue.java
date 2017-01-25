package me.zero.client.api.value;

/**
 * Simple interface for Values
 *
 * @since 1.0
 *
 * Created by Brady on 1/23/2017.
 */
interface IValue<T> {

    /**
     * @since 1.0
     *
     * @return The Value of this Object
     */
    T getValue();

    /**
     * Sets the Value of this Object
     *
     * @since 1.0
     *
     * @param value The new value
     */
    void setValue(T value);
}
