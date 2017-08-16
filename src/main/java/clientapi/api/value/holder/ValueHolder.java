/*
 * Copyright 2017 ZeroMemes
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

package clientapi.api.value.holder;

import clientapi.api.value.Value;
import com.google.common.collect.Sets;
import clientapi.api.value.Values;

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
