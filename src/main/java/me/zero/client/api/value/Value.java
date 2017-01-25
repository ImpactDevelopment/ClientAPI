package me.zero.client.api.value;

import me.zero.client.api.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * The implementation of IValue
 *
 * @see me.zero.client.api.value.IValue
 *
 * @since 1.0
 *
 * Created by Brady on 1/23/2017.
 */
public class Value<T> implements IValue<T> {

    /**
     * Name of the Value
     */
    private String name;

    /**
     * The Object that the field representing the Value is inside
     */
    private Object object;

    /**
     * The Field representing the Value
     */
    private Field field;

    public Value(String name, Object object, Field field) {
        this.name = name;
        this.object = object;
        this.field = field;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getValue() {
        return (T) ReflectionUtils.getField(object, field);
    }

    @Override
    public void setValue(T value) {
        ReflectionUtils.setField(object, field, value);
    }

    /**
     * @since 1.0
     *
     * @return The name of the value
     */
    public String getName() {
        return this.name;
    }
}
