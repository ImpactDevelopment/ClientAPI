package me.zero.client.api.util;

/**
 * Created by Brady on 1/23/2017.
 */
public class MathUtils {

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
