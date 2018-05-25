/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Util file used to clean up usages of the
 * reflection api throughout the client api
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
 */
public final class ReflectionUtils {

    private ReflectionUtils() {}

    /**
     * Finds the field from the specified object's class
     *
     * @param object An object of the defining class
     * @param field The field's name
     */
    public static Field findField(Object object, String field) {
        return findField(object.getClass(), field);
    }

    /**
     * Finds the field from the specified class
     *
     * @param clazz The defining class
     * @param field The field's name
     */
    public static Field findField(Class<?> clazz, String field) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.getName().equals(field)).findFirst().orElse(null);
    }

    /**
     * Gets the value of a field from an Object
     *
     * @param object Object that field belongs to
     * @param fieldName Field that is being retrieved
     * @return The value of the field
     */
    public static Object getField(Object object, String fieldName) {
        return getField(object, object.getClass(), fieldName);
    }

    /**
     * Gets the value of a field from an Object
     *
     * @param object Object that field belongs to
     * @param clazz The class the field is being defined by
     * @param fieldName Field that is being retrieved
     * @return The value of the field
     */
    public static Object getField(Object object, Class<?> clazz, String fieldName) {
        if (!clazz.isAssignableFrom(object.getClass()))
            return null;

        return getField(object, findField(clazz, fieldName));
    }

    /**
     * Gets the value of a field from an Object
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
        } catch (NullPointerException | IllegalAccessException ignored) {}
        return value;
    }

    /**
     * Sets the value of a field in an object
     *
     * @param object The object that the field belongs to
     * @param fieldName The name of the field being set
     * @param value The value that the field is being set to
     * @return true if setting the field was successful, false if otherwise
     */
    public static boolean setField(Object object, String fieldName, Object value) {
        return setField(object, object.getClass(), fieldName, value);
    }

    /**
     * Sets the value of a field in an object
     *
     * @param object The object that the field belongs to
     * @param clazz The class that the field is defined by
     * @param fieldName The name of the field being set
     * @param value The value that the field is being set to
     * @return true if setting the field was successful, false if otherwise
     */
    public static boolean setField(Object object, Class<?> clazz, String fieldName, Object value) {
        if (!clazz.isAssignableFrom(object.getClass()))
            return false;

        Field field = Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.getName().equals(fieldName)).findFirst().orElse(null);

        return setField(object, field, value);
    }

    /**
     * Sets the value of a field in an object
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
        } catch (NullPointerException | IllegalAccessException ignored) {}
        return false;
    }

    /**
     * Gets a method from the specified object with the
     * specified name and parameters
     *
     * @param clazz Class containing method
     * @param name Name of the method
     * @param parameters Paramaters of the method, as classes
     * @return The method, if found, null if not
     */
    public static Method findMethod(Class<?> clazz, String name, Class<?>... parameters) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(name) && Arrays.deepEquals(method.getParameterTypes(), parameters)) {
                return method;
            }
        }
        return null;
    }

    /**
     * Invokes the speicfied method with the specified parameters
     *
     * @param object Object containing method
     * @param method Method itself
     * @param parameters The parameters to pass to the method
     * @return Whether or not the operation was successful
     */
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
