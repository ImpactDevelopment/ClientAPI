package clientapi.value.type;

import clientapi.value.Value;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

/**
 * @author Brady
 * @since 12/1/2017 7:12 PM
 */
public final class EnumType<T extends Enum<?>> extends Value<T> {

    private final T[] values;

    public EnumType(String name, String parent, String id, String description, Object object, Field field, Class<T> enumClass) {
        super(name, parent, id, description, object, field);
        this.values = enumClass.getEnumConstants();
    }

    /**
     * Sets value to the next one in the set
     */
    public final void next() {
        int index = ArrayUtils.indexOf(values, getValue());
        if (++index >= values.length)
            index = 0;
        this.setValue(values[index]);
    }

    /**
     * Sets value to the last one in the set
     */
    public final void last() {
        int index = ArrayUtils.indexOf(values, getValue());
        if (--index < 0)
            index = values.length - 1;
        this.setValue(values[index]);
    }

    public final T[] getMultiValues() {
        return this.values;
    }
}
