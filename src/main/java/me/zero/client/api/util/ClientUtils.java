package me.zero.client.api.util;

import me.zero.client.api.exception.ArraySizeException;

import java.util.Arrays;

/**
 * Utils that the Client API uses
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/20/2017 12:00PM
 */
public final class ClientUtils {

    private ClientUtils() {}

    /**
     * Concatenates an array of generic arrays
     *
     * @since 1.0
     *
     * @param arrays The arrays being concatenated
     * @return The concatenated array
     */
    @SafeVarargs
    public static <T> T[] concat(T[]... arrays){
        if (arrays.length < 2)
            throw new ArraySizeException("At least 2 arrays should be supplied");

        T[] result = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            T[] newArray = Arrays.copyOf(result, result.length + arrays[i].length);
            System.arraycopy(arrays[i], 0, newArray, result.length, arrays[i].length);
            result = newArray;
        }

        return result;
    }

    /**
     * Checks if a generic list contains any null members.
     *
     * @since 1.0
     *
     * @param members The Members to be Checked
     */
    @SafeVarargs
    public static <T> boolean containsNull(T... members) {
        for (T member : members)
            if (member == null)
                return true;
        return false;
    }

    /**
     * Gets and returns the class that called the method that is calling this method.
     *
     * @since 1.0
     *
     * @return The class found
     */
    public static Class<?> traceSource() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length > 3) {
            try {
                return Class.forName(elements[3].getClassName());
            } catch (ClassNotFoundException e) {}
        }
        return null;
    }

    /**
     * Returns the Index of the specified Object in the Array, returns -1 if the
     * object is not found in the array
     *
     * @since 1.0
     *
     * @param object Object to be searched for
     * @param array Array to search for object in
     * @return Index of the Object in the Array
     */
    public static <T> int indexOf(T object, T[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns if the specified object is present in the specified array
     *
     * @since 1.0
     *
     * @param object Object to check if is present in Array
     * @param array Array that may/may not contain the Object
     * @return true if the specified object is in the specified array, false if not
     */
    public static <T> boolean arrayContains(T object, T[] array) {
        return indexOf(object, array) != -1;
    }

    /**
     * Returns the specified Object from the Array, if the object is present in
     * the array. If not, the first object present in the array will be
     * returned, if the size of the array is greater than 1, if not, the
     * specified Object will be returned.
     *
     * @since 1.0
     *
     * @param object The value trying to be set
     * @param array The array that may/may not contain the value
     * @return The value found from the array
     */
    public static <T> T objectFrom(T object, T[] array) {
        int index = indexOf(object, array);
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
}
