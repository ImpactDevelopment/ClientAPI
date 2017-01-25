package me.zero.client.api.value.type;

import me.zero.client.api.util.MathUtils;
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
    private T minimum;

    /**
     * Maximum value of the number
     */
    private T maximum;

    public NumberType(String name, Object object, Field field, T minimum, T maximum) {
        super(name, object, field);
    }

    @Override
    public void setValue(T value) {
        super.setValue((T) MathUtils.clamp(value, minimum, maximum));
    }

    /**
     * @since 1.0
     *
     * @return The minimum value of the number
     */
    public T getMinimum() {
        return this.minimum;
    }

    /**
     * @since 1.0
     *
     * @return The maximum value of this number
     */
    public T getMaximum() {
        return this.maximum;
    }
}
