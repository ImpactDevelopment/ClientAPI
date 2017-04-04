package me.zero.client.api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

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
    public static <T extends Number> T clamp(T value, T minimum, T maximum) {
        if (minimum.doubleValue() > maximum.doubleValue()) {
            T temp = minimum;
            minimum = maximum;
            maximum = temp;
        }
        if (value.doubleValue() > maximum.doubleValue()) value = maximum;
        if (value.doubleValue() < minimum.doubleValue()) value = minimum;
        return value;
    }

    /**
     * Rounds a number to a specific place
     *
     * @since 1.0
     *
     * @param value Value being rounded
     * @param places Places being rounded
     * @return Rounded value
     */
    public static double roundToPlace(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Gets a random int between a min value and max value
     *
     * @since 1.0
     *
     * @param min The minimum value
     * @param max The maximum value
     * @return The random vakue
     */
    public static int randInt(int min, int max) {
        if (min > max) {
            int temp = max;
            max = min;
            min = temp;
        }
        return new Random().nextInt(max - min + 1) + min;
    }
}
