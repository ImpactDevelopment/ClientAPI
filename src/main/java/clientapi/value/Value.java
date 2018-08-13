/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.value;

import clientapi.util.interfaces.Mutable;
import clientapi.util.interfaces.impl.MergedMutable;
import clientapi.util.interfaces.impl.MutableField;
import clientapi.value.holder.ValueAccessor;
import me.zero.alpine.type.EventState;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation of IValue
 *
 * @see IValue
 *
 * @author Brady
 * @since 1/23/2017
 */
public class Value<T> implements IValue<T> {

    /**
     * List of child values
     */
    private final List<IValue> children = new ArrayList<>();

    /**
     * Cache of values by ID, faster than creating streams every time a value is retrieved
     */
    private final Map<String, IValue> valueCache = new HashMap<>();

    /**
     * A list of all of the {@code ValueChangeListeners} waiting for this value to change
     */
    private final Map<EventState, List<ValueChangeListener<T>>> valueChangeListeners = new HashMap<>();

    /**
     * Name of the Value
     */
    private final String name;

    /**
     * ID of the parent value, null if there isn't a parent
     */
    private final String parent;

    /**
     * Description of the Value
     */
    private final String id;

    /**
     * Description of the Value
     */
    private final String description;

    /**
     * The Object that the field representing the Value is inside
     */
    private final Object object;

    /**
     * The Field representing the Value
     */
    private final Field field;

    /**
     * A {@code Mutable} used to set/get this value.
     */
    private final Mutable<Object> mutable;

    public Value(String name, String parent, String id, String description, Object object, Field field) {
        // Value properties
        this.name = name;
        this.parent = parent != null && parent.length() > 0 ? parent : null;
        this.id = id;
        this.description = description;

        // Field reference
        this.object = object;
        this.field = field;

        // Accessor
        if (object instanceof ValueAccessor) {
            this.mutable = new MergedMutable<>(
                    ((ValueAccessor) object).getFieldSetter(field.getName()),
                    ((ValueAccessor) object).getFieldGetter(field.getName())
            );
        } else {
            this.mutable = new MutableField<>(object, field);
        }

        // Listeners
        for (EventState state : EventState.values()) {
            valueChangeListeners.put(state, new ArrayList<>());
        }
    }

    @Override
    public final String getParent() {
        return this.parent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getValue() {
        return (T) this.mutable.get();
    }

    @Override
    public void setValue(T value) {
        T oldValue = this.getValue();
        // Notify all PRE change listeners
        this.valueChangeListeners.get(EventState.PRE).forEach(listener -> listener.onValueChanged(this, oldValue, value));
        // Pass the new value to the change function
        this.mutable.accept(value);
        // Nofify all POST change listeners
        this.valueChangeListeners.get(EventState.POST).forEach(listener -> listener.onValueChanged(this, oldValue, value));
    }

    @Override
    public void addChangeListener(EventState state, ValueChangeListener<T> listener) {
        this.valueChangeListeners.get(state).add(listener);
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final String getID() {
        return this.id;
    }

    @Override
    public final boolean addValue(IValue value) {
        return getValue(value.getID()) == null && this.children.add(value);
    }

    @Override
    public final IValue getValue(String id) {
        return valueCache.computeIfAbsent(id, _id -> children.stream().filter(value -> value.getID().equals(_id)).findFirst().orElse(null));
    }

    @Override
    public final List<IValue> getValues() {
        return new ArrayList<>(this.children);
    }
}
