package me.zero.client.api.value.type;

import me.zero.client.api.util.ReflectionUtils;
import me.zero.client.api.util.interfaces.annotation.Label;
import me.zero.client.api.value.Value;
import me.zero.client.api.value.annotation.MultiValue;
import me.zero.client.api.value.annotation.NumberValue;

import java.lang.reflect.Field;

/**
 * Converts a Field to a Value, if possible.
 *
 * @since 1.0
 *
 * Created by Brady on 2/21/2017.
 */
public interface TypeResolver<T extends Value> {

    /**
     * Resolves a Value from it's respective Field
     *
     * @since 1.0
     *
     * @param parent The object containing the field
     * @param field The value field
     * @return The resolved Value
     */
    T resolve(Object parent, Field field);

    /**
     * Resolves Boolean Types
     */
    TypeResolver<BooleanType> BOOLEAN = (parent, field) -> {
        Label label = field.getAnnotation(Label.class);
        Boolean value = (Boolean) ReflectionUtils.getField(parent, field);
        if (value == null)
            value = false;

        BooleanType type = new BooleanType(label.name(), label.id(), label.description(), parent, field);
        type.setState(value);
        return type;
    };

    /**
     * Resolves String Types
     */
    TypeResolver<StringType> STRING = (parent, field) -> {
        Label label = field.getAnnotation(Label.class);
        return new StringType(label.name(), label.id(), label.description(), parent, field);
    };

    /**
     * Resolves Multi Types
     */
    TypeResolver<MultiType> MULTI = (parent, field) -> {
        Label label = field.getAnnotation(Label.class);
        MultiValue multi = field.getAnnotation(MultiValue.class);
        MultiType type = new MultiType(label.name(), label.id(), label.description(), parent, field, multi.value());
        if (type.getValue() == null)
            type.setValue(multi.value()[0]);
        return type;
    };

    /**
     * Resolves Number Types
     */
    TypeResolver<NumberType> NUMBER = (parent, field) -> {
        Label label = field.getAnnotation(Label.class);
        NumberValue num = field.getAnnotation(NumberValue.class);

        // Clean up
        if (field.getType() == Byte.class || field.getType() == Byte.TYPE) {
            return new NumberType<Byte>(label.name(), label.id(), label.description(), parent, field, (byte) num.min(), (byte) num.max()){ };
        } else if (field.getType() == Short.class || field.getType() == Short.TYPE) {
            return new NumberType<Short>(label.name(), label.id(), label.description(), parent, field, (short) num.min(), (short) num.max()){ };
        } else if (field.getType() == Integer.class || field.getType() == Integer.TYPE) {
            return new NumberType<Integer>(label.name(), label.id(), label.description(), parent, field, (int) num.min(), (int) num.max()){ };
        } else if (field.getType() == Long.class || field.getType() == Long.TYPE) {
            return new NumberType<Long>(label.name(), label.id(), label.description(), parent, field, (long) num.min(), (long) num.max()){ };
        } else if (field.getType() == Float.class || field.getType() == Float.TYPE) {
            return new NumberType<Float>(label.name(), label.id(), label.description(), parent, field, (float) num.min(), (float) num.max()){ };
        } else if (field.getType() == Double.class || field.getType() == Double.TYPE) {
            return new NumberType<Double>(label.name(), label.id(), label.description(), parent, field, num.min(), num.max()){ };
        }

        return null;
    };
}
