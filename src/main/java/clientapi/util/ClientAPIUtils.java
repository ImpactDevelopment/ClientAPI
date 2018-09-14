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

import clientapi.manage.Manager;
import clientapi.module.Module;
import clientapi.module.annotation.Category;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * Contains methods that ClientAPI uses throughout its code.
 *
 * @author Brady
 * @since 1/20/2017
 */
public final class ClientAPIUtils {

    private ClientAPIUtils() {}

    /**
     * Concatenates an array of generic arrays
     *
     * @param arrays The arrays being concatenated
     * @param <T> The array type
     * @return The concatenated array
     */
    @SafeVarargs
    public static <T> T[] concat(T[]... arrays){
        if (arrays.length < 2)
            throw new IllegalArgumentException("At least 2 arrays should be supplied");

        T[] result = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            T[] newArray = Arrays.copyOf(result, result.length + arrays[i].length);
            System.arraycopy(arrays[i], 0, newArray, result.length, arrays[i].length);
            result = newArray;
        }

        return result;
    }

    /**
     * Checks if a generic array contains any null members.
     *
     * @param members The Members to be Checked
     * @param <T> The array type
     * @return Whether or not the array contained any null members
     */
    @SafeVarargs
    public static <T> boolean containsNull(T... members) {
        for (T member : members)
            if (member == null)
                return true;
        return false;
    }

    /**
     * Checks if a string array contains the specified
     * string, non case-sensitive
     *
     * @param array Array of strings being checked
     * @param target String being searched for
     * @return Whether or not the specified array contained the target, case in-sensitive
     */
    public static boolean containsIgnoreCase(String[] array, String target) {
        Objects.requireNonNull(array);
        for (String string : array)
            if (string != null && string.equalsIgnoreCase(target))
                return true;
        return false;
    }

    /**
     * Gets and returns the class that called the method that is calling this method.
     *
     * @return The class found
     */
    public static Class<?> traceSource() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length > 3) {
            try {
                return Class.forName(elements[3].getClassName());
            } catch (ClassNotFoundException ignored) {}
        }
        return null;
    }

    /**
     * Returns the specified Object from the Array if the object is present in
     * the array. If not, the first object present in the array will be
     * returned if the size of the array is greater than 1, if not, the
     * specified Object will be returned.
     *
     * @param object The value trying to be set
     * @param array The array that may/may not contain the value
     * @param <T> The array type
     * @return The value found from the array
     */
    public static <T> T objectFrom(T object, T[] array) {
        int index = ArrayUtils.indexOf(array, object);
        if (index != -1) {
            return array[index];
        } else {
            if (array.length > 0) {
                return array[0];
            } else {
                return object;
            }
        }
    }

    /**
     * Determines if the specified array of generics contains
     * matching members.
     *
     * @param objects Objects being checked
     * @param <T> The array type
     * @return Whether or not all members match
     */
    @SafeVarargs
    public static <T> boolean matchingMembers(T... objects) {
        T baseline = null;
        boolean set = false;
        for (T object : objects) {
            if (!set)
                baseline = object;
            if (object != baseline)
                return false;
            set = true;
        }
        return true;
    }

    /**
     * Gets the categories that are represented by a manager containing modules.
     *
     * @param moduleManager The manager containing modules
     *
     * @return The categories
     */
    public static Collection<Class<?>> getCategories(Manager<Module> moduleManager) {
        return ClientAPIUtils.getCategories(moduleManager, false);
    }

    /**
     * Gets the categories that are represented by a manager containing modules.
     *
     * @param moduleManager The manager containing modules
     * @param sort Whether or not to sort alphabetically
     * @return The categories
     */
    public static Collection<Class<?>> getCategories(Manager<Module> moduleManager, boolean sort) {
        LinkedHashSet<Class<?>> categories = new LinkedHashSet<>();
        moduleManager.stream().map(Module::getType).forEach(categories::add);

        if (sort) {
            List<Class<?>> sorted = new ArrayList<>(categories);
            sorted.sort((c1, c2) -> String.CASE_INSENSITIVE_ORDER.compare(
                    c1.getAnnotation(Category.class).name(),
                    c2.getAnnotation(Category.class).name()
            ));
            return sorted;
        }

        return categories;
    }

    /**
     * Sleeps the current thread for the specified length
     * of time represented as milliseconds without the need
     * for a try/catch enclosure.
     *
     * @see Thread#sleep(long)
     *
     * @param ms The milliseconds to sleep
     * @return Whether or not an {@link InterruptedException} was thrown by {@link Thread#sleep(long)}
     */
    public static boolean sleep(long ms) {
        if (ms <= 0) {
            return true;
        }

        try {
            Thread.sleep(ms);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
