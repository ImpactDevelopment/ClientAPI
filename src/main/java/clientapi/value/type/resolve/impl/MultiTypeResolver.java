package clientapi.value.type.resolve.impl;

import clientapi.util.annotation.Label;
import clientapi.value.annotation.MultiValue;
import clientapi.value.type.MultiType;
import clientapi.value.type.resolve.TypeResolver;

import java.lang.reflect.Field;

/**
 * Default implementation of {@code TypeResolver} used to parse {@code MultiType} fields
 *
 * @author Brady
 * @since 2/8/2018 3:24 PM
 */
public final class MultiTypeResolver implements TypeResolver<MultiType> {

    @Override
    public final MultiType resolve(Object parent, Field field) {
        Label label = field.getAnnotation(Label.class);
        MultiValue multi = field.getAnnotation(MultiValue.class);

        MultiType type = new MultiType(label.name(), label.parent(), label.id(), label.description(), parent, field, multi.value());
        if (type.getValue() == null)
            type.setValue(type.getMultiValues()[0]);

        return type;
    }
}
