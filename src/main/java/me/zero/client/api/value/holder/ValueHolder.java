package me.zero.client.api.value.holder;

import com.google.common.collect.Sets;
import me.zero.client.api.value.Value;
import me.zero.client.api.value.Values;

import java.util.Collection;

/**
 * Implementation of {@code IValueHolder}
 *
 * @see IValueHolder
 *
 * @author Brady
 * @since 5/12/2017 2:52 PM
 */
public class ValueHolder implements IValueHolder {

    /**
     * Values that are "Held" by this holder
     */
    private final Collection<Value> values = Sets.newLinkedHashSet();

    protected ValueHolder() {
        Values.discover(this);
    }

    @Override
    public final boolean addValue(Value value) {
        return getValue(value.getId()) == null && this.values.add(value);

    }

    @Override
    public final Value getValue(String id) {
        return values.stream().filter(value -> value.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public final Collection<Value> getValues() {
        return Sets.newLinkedHashSet(this.values);
    }
}
