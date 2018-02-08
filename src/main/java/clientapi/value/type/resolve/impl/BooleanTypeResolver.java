package clientapi.value.type.resolve.impl;

import clientapi.util.ReflectionUtils;
import clientapi.util.annotation.Label;
import clientapi.value.type.BooleanType;
import clientapi.value.type.resolve.TypeResolver;

import java.lang.reflect.Field;

/**
 * Default implementation of {@code TypeResolver} used to parse {@code BooleanType} fields
 *
 * @author Brady
 * @since 2/8/2018 3:18 PM
 */
public final class BooleanTypeResolver implements TypeResolver<BooleanType> {

    @Override
    public final BooleanType resolve(Object parent, Field field) {
        Label label = field.getAnnotation(Label.class);
        Boolean value = (Boolean) ReflectionUtils.getField(parent, field);
        if (value == null)
            value = false;

        BooleanType type = new BooleanType(label.name(), label.parent(), label.id(), label.description(), parent, field);
        type.setState(value);
        return type;
    }
}
