package me.zero.client.api.util;

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
}
