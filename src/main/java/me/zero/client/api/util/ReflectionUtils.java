package me.zero.client.api.util;

import java.lang.reflect.Field;

/**
 * Created by Brady on 1/23/2017.
 */
public class ReflectionUtils {

    public static Object getField(Object object, Field field) {
        Object value = null;
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            value = field.get(object);
            field.setAccessible(accessible);
        } catch (IllegalAccessException e) {
            // This should never happen because we're setting access
        }
        return value;
    }

    public static boolean setField(Object object, Field field, Object value) {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            field.set(object, value);
            field.setAccessible(accessible);
            return true;
        } catch (IllegalAccessException e) {
            // This should never happen because we're setting access
        }
        return false;
    }
}
