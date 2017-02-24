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

    /**
     * Discovers all of the Values in a node, then registers them
     *
     * @since 1.0
     *
     * @param node Node being scanned
     */
    public static void discover(Node node) {
        Arrays.stream(node.getClass().getDeclaredFields())
                .filter(Values::hasValueAnnotation)
                .forEach(field -> node.addValue(getValue(node, field)));
    }

    /**
     * Returns whether or not the specified field
     * has a value annotation or not.
     *
     * @since 1.0
     *
     * @param field Field being checked
     * @return If the field has a value annotation
     */
    private static boolean hasValueAnnotation(Field field) {
        return getValueAnnotation(field) != null;
    }

    /**
     * Gets the class of the value annotation belonging
     * to a field, null if there is none.
     *
     * @since 1.0
     *
     * @param field Field being checked
     * @return The value annotation of the field
     */
    private static Class<? extends Annotation> getValueAnnotation(Field field) {
        if (field.isAnnotationPresent(Label.class)) {
            Annotation a = Arrays.stream(field.getDeclaredAnnotations())
                    .filter(annotation -> annotation.getClass().getCanonicalName().startsWith("me.zero.client.api.value.annotation"))
                    .findFirst()
                    .orElse(null);
            if (a != null)
                return a.getClass();
        }
        return null;
    }

    /**
     * Executes checks before using the TypeResolver to
     * get the Value from the Field.
     *
     * @since 1.0
     *
     * @param parent Object containing value field
     * @param field Field representing value
     * @return The resolved field
     */
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
