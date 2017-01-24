package me.zero.client.api.value;

import me.zero.client.api.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Created by Brady on 1/23/2017.
 */
public class Value<T> implements IValue<T> {

    private String name;
    private Object object;
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
}
