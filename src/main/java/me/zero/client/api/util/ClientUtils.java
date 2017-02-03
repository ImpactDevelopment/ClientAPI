package me.zero.client.api.util;

import me.zero.client.api.exception.ActionNotValidException;

import java.util.Arrays;

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
     * @param arrays The arrays being concatenated
     * @return The concatenated array
     */
    public static <T> T[] concat(T[]... arrays) {
        if (arrays.length < 2)
            throw new ActionNotValidException("At least 2 arrays should be supplied");

        T[] result = arrays[0];
        for (int i = 1; i < arrays.length; i++) {
            result = concat(result, arrays[i]);
        }

        return result;
    }

    /**
     * Concatenates 2 generic arrays
     *
     * @param first The first array
     * @param second The seccond array
     * @return The concatenated array
     */
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    /**
     * Checks if a generic list contains any null members.
     *
     * @param members The Members to be Checked
     */
    public static <T> boolean containsNull(T... members) {
        for (T member : members)
            if (member == null)
                return true;
        return false;
    }
}
