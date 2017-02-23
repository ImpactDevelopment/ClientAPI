package me.zero.client.api.value;

import me.zero.client.api.manage.Node;
import me.zero.client.api.util.interfaces.annotation.Label;
import me.zero.client.api.value.annotation.BooleanValue;
import me.zero.client.api.value.annotation.NumberValue;
import me.zero.client.api.value.annotation.StringValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Class used to discover values from various holders
 *
 * @since 1.0
 *
 * Created by Brady on 2/21/2017.
 */
public class Values {

    public static void discover(Node node) {
        Arrays.stream(node.getClass().getDeclaredFields())
                .filter(Values::hasValueAnnotation)
                .forEach(field -> node.addValue(getValue(node, field)));
    }

    private static boolean hasValueAnnotation(Field field) {
        return getValueAnnotation(field) != null;
    }

    private static Class<? extends Annotation> getValueAnnotation(Field field) {
        if (field.isAnnotationPresent(Label.class))
            return Arrays.stream(field.getDeclaredAnnotations())
                    .filter(annotation -> annotation.getClass().getCanonicalName().startsWith("me.zero.client.api.value.annotation"))
                    .findFirst().orElse(null).getClass();

        return null;
    }

    private static Value getValue(Object parent, Field field) {
        Class<? extends Annotation> anno = getValueAnnotation(field);

        if (anno == BooleanValue.class && field.getType() == Boolean.class) {
            return TypeResolver.BOOLEAN.resolve(parent, field);
        } else if (anno == NumberValue.class) {
            // TODO: Create Resolver
        } else if (anno == StringValue.class && field.getType() == String.class) {
            return TypeResolver.STRING.resolve(parent, field);
        }
        return null;
    }
}
