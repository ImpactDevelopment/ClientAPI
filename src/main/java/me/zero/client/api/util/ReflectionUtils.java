package me.zero.client.api.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Util file used to clean up usages of the
 * reflection api throughout the client api
 *
 * @since 1.0
 *
 * Created by Brady on 1/23/2017.
 */
public class ReflectionUtils {

    /**
     * Gets the value of a field from an Object
     *
     * @since 1.0
     *
     * @param object Object that field belongs to
     * @param fieldName Field that is being retrieved
     * @return The value of the field
     */
    public static Object getField(Object object, String fieldName) {
        Field field = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);

        return getField(object, field);
    }

    /**
     * Gets the value of a field from an Object
     *
     * @since 1.0
     *
     * @param object Object that field belongs to
     * @param field Field that is being retrieved
     * @return The value of the field
     */
    public static Object getField(Object object, Field field) {
        Object value = null;
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            value = field.get(object);
            field.setAccessible(accessible);
        } catch (NullPointerException | IllegalAccessException e) {
            // Should only throw nullpointer, access is being set
        }
        return value;
    }

    /**
     * Sets the value of a field in an object
     *
     * @since 1.0
     *
     * @param object The object that the field belongs to
     * @param fieldName The name of the field being set
     * @param value The value that the field is being set to
     * @return true if setting the field was successful, false if otherwise
     */
    public static boolean setField(Object object, String fieldName, Object value) {
        Field field = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);

        return setField(object, field, value);
    }

    /**
     * Sets the value of a field in an object
     *
     * @since 1.0
     *
     * @param object The object that the field belongs to
     * @param field The field being set
     * @param value The value that the field is being set to
     * @return true if setting the field was successful, false if otherwise
     */
    public static boolean setField(Object object, Field field, Object value) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(object, value);
            field.setAccessible(accessible);
            return true;
        } catch (NullPointerException | IllegalAccessException e) {
            // Should only throw nullpointer, access is being set
        }
        return false;
    }

    public static Method getMethod(Object object, String name, Class<?>... parameters) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.getName().equals(name) && method.getParameterTypes().length == parameters.length) {
                boolean match = true;
                for (int i = 0; i < parameters.length; i++) {
                    if (method.getParameterTypes()[i] != parameters[i])
                        match = false;
                }
                if (match)
                    return method;
            }
        }
        return null;
    }

    public static boolean callMethod(Object object, Method method, Object... parameters) {
        try {
            boolean accessible = method.isAccessible();
            method.setAccessible(true);
            method.invoke(object, parameters);
            method.setAccessible(accessible);
            return true;
        } catch (IllegalAccessException | InvocationTargetException e) {
            return false;
        }
    }
}
