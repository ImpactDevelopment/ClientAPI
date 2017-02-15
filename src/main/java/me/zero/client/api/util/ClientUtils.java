package me.zero.client.api.util;

import me.zero.client.api.exception.ActionNotValidException;

import java.util.Arrays;
import java.util.List;

/**
 * Utils that the Client API uses
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public class ClientUtils {

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
            throw new ActionNotValidException("At least 2 arrays should be supplied");

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
}
