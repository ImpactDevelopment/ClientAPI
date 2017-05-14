package me.zero.client.api.value.holder;

import me.zero.client.api.value.Value;

import java.util.Collection;

/**
 * The "shell" for a value holder
 *
 * @author Brady
 * @since 5/12/2017 2:56 PM
 */
public interface IValueHolder {

    /**
     * Adds a value to this holder, should
     * only be added if there isn't already
     * a value with the same id.
     *
     * @param value Value being added
     * @return true if the value was able to be added
     */
    boolean addValue(Value value);

    /**
     * Gets the value that this holder may
     * or may not hold, from an id.
     *
     * @param id The id of the target value
     * @return The value, if it is found, otherwise null
     */
    Value getValue(String id);

    /**
     * @return All of the values that this holder "holds"
     */
    Collection<Value> getValues();
}
