package me.zero.client.api.value.type;

import me.zero.client.api.util.math.MathUtils;
import me.zero.client.api.value.Value;

import java.lang.reflect.Field;

/**
 * Basic type for Number values
 *
 * @see me.zero.client.api.value.annotation.NumberValue
 *
 * @since 1.0
 *
 * Created by Brady on 1/23/2017.
 */
public class NumberType<T extends Number> extends Value<T> {

    /**
     * Minimum value of the number
     */
    private final T minimum;

    /**
     * Maximum value of the number
     */
    private final T maximum;

    public NumberType(String name, String id, String description, Object object, Field field, T minimum, T maximum) {
        super(name, id, description, object, field);
        this.minimum = minimum;
        this.maximum = maximum;
    }

    @Override
    public final T getValue() {
        return super.getValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void setValue(T value) {
        super.setValue(cast(MathUtils.clamp(value, minimum, maximum)));
    }

    /**
     * @since 1.0
     *
     * @return The minimum value of the number
     */
    public final T getMinimum() {
        return this.minimum;
    }

    /**
     * @since 1.0
     *
     * @return The maximum value of this number
     */
    public final T getMaximum() {
        return this.maximum;
    }

    /**
     * Increments the value by the number specified
     * times the number range divided by 10
     *
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public final void increment(float multiplier) {
        double range = maximum.doubleValue() - minimum.doubleValue();
        this.setValue((T) (Number) (this.getValue().doubleValue() + (range / 10.0 * multiplier)));
    }

    /**
     * Decrement the value by the number specified
     * times the number range divided by 10
     *
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public final void decrement(float multiplier) {
        this.increment(-multiplier);
    }

    /**
     * Used to cast a random number type to a
     * compatible type for this value, this method
     * is a complete mess and should be fixed soon.
     *
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    private T cast(Number val) {
        Class<?> clazz = getValue().getClass();
        if (clazz == Byte.class || clazz == Byte.TYPE) {
            return (T) Byte.valueOf(val.byteValue());
        } else if (clazz == Short.class || clazz == Short.TYPE) {
            return (T) Short.valueOf(val.shortValue());
        } else if (clazz == Integer.class || clazz == Integer.TYPE) {
            return (T) Integer.valueOf(val.intValue());
        } else if (clazz == Long.class || clazz == Long.TYPE) {
            return (T) Long.valueOf(val.longValue());
        } else if (clazz == Float.class || clazz == Float.TYPE) {
            return (T) Float.valueOf(val.floatValue());
        } else if (clazz == Double.class || clazz == Double.TYPE) {
            return (T) Double.valueOf(val.doubleValue());
        }
        throw new RuntimeException("A number that isn't a number? Okay, Java.");
    }
}
