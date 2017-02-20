package me.zero.client.api.value;

import com.google.common.collect.Sets;
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
 * Created by Brady on 1/23/2017.
 */
public class Value<T> implements IValue<T>, Nameable {

    /**
     * Name of the Value
     */
    private String name;

    /**
     * Description of the Value
     */
    private String description;

    /**
     * The Object that the field representing the Value is inside
     */
    private Object object;

    /**
     * The Field representing the Value
     */
    private Field field;

    public Value(String name, String description, Object object, Field field) {
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

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
