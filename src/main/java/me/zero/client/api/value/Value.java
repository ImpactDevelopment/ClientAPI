package me.zero.client.api.value;

import me.zero.client.api.util.ReflectionUtils;
import me.zero.client.api.util.interfaces.Nameable;

import java.lang.reflect.Field;

/**
 * The implementation of IValue
 *
 * @see me.zero.client.api.value.IValue
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/23/2017 12:00PM
 */
public class Value<T> implements IValue<T>, Nameable {

    /**
     * Name of the Value
     */
    private final String name;

    /**
     * Description of the Value
     */
    private final String id;

    /**
     * Description of the Value
     */
    private final String description;

    /**
     * The Object that the field representing the Value is inside
     */
    private final Object object;

    /**
     * The Field representing the Value
     */
    private final Field field;

    public Value(String name, String id, String description, Object object, Field field) {
        this.name = name;
        this.id = id;
        this.description = description;
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

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    public final String getId() {
        return this.id;
    }
}
