package me.zero.client.api.value.type;

import me.zero.client.api.util.ClientUtils;
import me.zero.client.api.value.Value;

import java.lang.reflect.Field;

/**
 * A value that can have a specific set of values
 *
 * @since 1.0
 *
 * Created by Brady on 2/24/2017.
 */
public class MultiType extends Value<String> {

    /**
     * Different values
     */
    private String[] values;

    public MultiType(String name, String id, String description, Object object, Field field, String[] values) {
        super(name, id, description, object, field);
        this.values = values;
        this.setValue(values[0]);
    }

    @Override
    public void setValue(String value) {
        super.setValue(ClientUtils.objectFrom(value, values));
    }

    /**
     * Sets value to the next one in the set
     *
     * @since 1.0
     */
    public void next() {
        int index = ClientUtils.indexOf(getValue(), values);
        index++;
        if (index >= values.length) {
            index = 0;
        }
        this.setValue(values[index]);
    }

    /**
     * Sets value to the last one in the set
     *
     * @since 1.0
     */
    public void last() {
        int index = ClientUtils.indexOf(getValue(), values);
        index--;
        if (index < 0) {
            index = values.length - 1;
        }
        this.setValue(values[index]);
    }

    /**
     * @since 1.0
     *
     * @return All possible values for this MultiType
     */
    public String[] getValues() {
        return this.values;
    }
}
