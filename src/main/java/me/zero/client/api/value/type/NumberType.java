package me.zero.client.api.value.type;

import me.zero.client.api.util.MathUtils;
import me.zero.client.api.value.Value;

import java.lang.reflect.Field;

/**
 * Created by Brady on 1/23/2017.
 */
public class NumberType<T extends Number> extends Value<T> {

    private T minimum, maximum;

    public NumberType(String name, Object object, Field field, T minimum, T maximum) {
        super(name, object, field);
    }

    @Override
    public void setValue(T value) {
        super.setValue((T) MathUtils.clamp(value, minimum, maximum));
    }

    public T getMinimum() {
        return this.minimum;
    }

    public T getMaximum() {
        return this.maximum;
    }
}
