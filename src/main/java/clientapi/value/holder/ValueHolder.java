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

package clientapi.value.holder;

import clientapi.value.IValue;
import clientapi.value.Values;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@code IValueHolder}, intended for
 * use in classes containing annotated values.
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
    private final List<IValue> values = new ArrayList<>();

    /**
     * Cache of values by ID, faster than creating streams every time a value is retrieved
     */
    private final Map<String, IValue> valueCache = new HashMap<>();

    protected ValueHolder() {
        Values.discover(this).forEach(this::addValue);
    }

    @Override
    public final boolean addValue(IValue value) {
        return getValue(value.getId()) == null && this.values.add(value);
    }

    @Override
    public final IValue getValue(String id) {
        return valueCache.computeIfAbsent(id, _id -> values.stream().filter(value -> value.getId().equals(_id)).findFirst().orElse(null));
    }

    @Override
    public final List<IValue> getValues() {
        return new ArrayList<>(this.values);
    }
}
