package me.zero.client.api.util;

/**
 * Utils used for mathematical operations.
 *
 * @since 1.0
 *
 * Created by Brady on 1/23/2017.
 */
public class MathUtils {

    /**
     * Clamps a Number
     *
     * @since 1.0
     *
     * @param value The value being clamped
     * @param minimum The minimum possible value
     * @param maximum The maximum possible value
     * @return The clamped value
     */
    public static Number clamp(Number value, Number minimum, Number maximum) {
        if (minimum.doubleValue() > maximum.doubleValue()) {
            Number temp = minimum;
            minimum = maximum;
            maximum = temp;
        }
        if (value.doubleValue() > maximum.doubleValue()) value = maximum;
        if (value.doubleValue() < minimum.doubleValue()) value = minimum;
        return value;
    }
}
