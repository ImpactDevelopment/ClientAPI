package clientapi.value.type.resolve.impl;

import clientapi.util.annotation.Label;
import clientapi.value.type.StringType;
import clientapi.value.type.resolve.TypeResolver;

import java.lang.reflect.Field;

/**
 * Default implementation of {@code TypeResolver} used to parse {@code StringType} fields
 *
 * @author Brady
 * @since 2/8/2018 3:23 PM
 */
public final class StringTypeResolver implements TypeResolver<StringType> {

    @Override
    public final StringType resolve(Object parent, Field field) {
        Label label = field.getAnnotation(Label.class);
        return new StringType(label.name(), label.parent(), label.id(), label.description(), parent, field);
    }
}
