package me.zero.client.api.value;

import me.zero.client.api.util.ReflectionUtils;
import me.zero.client.api.util.interfaces.annotation.Label;
import me.zero.client.api.value.type.BooleanType;
import me.zero.client.api.value.type.StringType;

import java.lang.reflect.Field;

/**
 * Converts a Field to a Value, if possible.
 *
 * @since 1.0
 *
 * Created by Brady on 2/21/2017.
 */
public interface TypeResolver<T extends Value> {

    T resolve(Object parent, Field field);

    TypeResolver<BooleanType> BOOLEAN = (parent, field) -> {
        Label label = field.getAnnotation(Label.class);
        Boolean value = (Boolean) ReflectionUtils.getField(parent, field);
        if (value == null)
            value = false;

        BooleanType type = new BooleanType(label.name(), label.description(), parent, field);
        type.setState(value);
        return type;
    };

    TypeResolver<StringType> STRING = (parent, field) -> {
        Label label = field.getAnnotation(Label.class);
        StringType type = new StringType(label.name(), label.description(), parent, field);
        type.setValue((String) ReflectionUtils.getField(parent, field));
        return type;
    };
}
