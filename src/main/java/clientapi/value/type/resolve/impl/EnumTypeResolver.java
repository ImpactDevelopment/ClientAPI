package clientapi.value.type.resolve.impl;

import clientapi.util.annotation.Label;
import clientapi.value.type.EnumType;
import clientapi.value.type.resolve.TypeResolver;

import java.lang.reflect.Field;

/**
 * Default implementation of {@code TypeResolver} used to parse {@code EnumType} fields
 *
 * @author Brady
 * @since 2/8/2018 3:26 PM
 */
public final class EnumTypeResolver implements TypeResolver<EnumType> {

    @SuppressWarnings("unchecked")
    @Override
    public final EnumType resolve(Object parent, Field field) {
        Label label = field.getAnnotation(Label.class);

        Class<?> enumType = field.getType();

        EnumType type = new EnumType(label.name(), label.parent(), label.id(), label.description(), parent, field, enumType);
        if (type.getValue() == null)
            type.setValue(type.getMultiValues()[0]);

        return type;
    }
}
