/*
 * Copyright 2017 ImpactDevelopment
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

import clientapi.util.ReflectionUtils;
import clientapi.value.holder.ValueAccessor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * The implementation of IValue
 *
 * @see IValue
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
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
     * Whether or not to use direct calls to retrieve field values.
     */
    private final boolean direct;

    /**
     * Setter for this value's field. Should be {@code null} if direct is {@code false}
     */
    private final Consumer<Object> setter;

    /**
     * Getter for this value's field. Should be (@code null} if direct is {@code false}
     */
    private final Supplier<Object> getter;

    public Value(String name, String parent, String id, String description, Object object, Field field) {
        // Value properties
        this.name = name;
        this.parent = parent.length() > 0 ? parent : null;
        this.id = id;
        this.description = description;

        // Field reference
        this.object = object;
        this.field = field;

        // Accessor
        this.direct = object instanceof ValueAccessor;
        this.setter = direct ? ((ValueAccessor) object).getFieldSetter(id) : null;
        this.getter = direct ? ((ValueAccessor) object).getFieldGetter(id) : null;
    }

    @Override
    public String getParent() {
        return this.parent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getValue() {
        if (direct)
            return (T) getter.get();
        else
            return (T) ReflectionUtils.getField(object, field);
    }

    @Override
    public void setValue(T value) {
        if (direct)
            setter.accept(value);
        else
            ReflectionUtils.setField(object, field, value);
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
    public final String getId() {
        return this.id;
    }

    @Override
    public final boolean addValue(IValue value) {
        return getValue(value.getId()) == null && this.children.add(value);
    }

    @Override
    public final IValue getValue(String id) {
        return valueCache.computeIfAbsent(id, _id -> children.stream().filter(value -> value.getId().equals(_id)).findFirst().orElse(null));
    }

    @Override
    public final List<IValue> getValues() {
        return new ArrayList<>(this.children);
    }
}
