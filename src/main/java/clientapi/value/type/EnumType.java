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

package clientapi.value.type;

import clientapi.util.interfaces.Cycleable;
import clientapi.value.Value;
import clientapi.value.annotation.EnumValue;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;

/**
 * Basic type for Enum values
 *
 * @see EnumValue
 *
 * @author Brady
 * @since 12/1/2017
 */
public final class EnumType<T extends Enum<?>> extends Value<T> implements Cycleable<T> {

    private final T[] values;

    public EnumType(String name, String parent, String id, String description, Object object, Field field, Class<T> enumClass) {
        super(name, parent, id, description, object, field);
        this.values = enumClass.getEnumConstants();
    }

    @Override
    public final T current() {
        return this.getValue();
    }

    @Override
    public final T next() {
        T value = peekNext();
        this.setValue(value);
        return value;
    }

    @Override
    public final T last() {
        T value = peekLast();
        this.setValue(value);
        return value;
    }

    @Override
    public final T peekNext() {
        int index = ArrayUtils.indexOf(values, getValue());
        if (++index >= values.length)
            index = 0;
        return values[index];
    }

    @Override
    public final T peekLast() {
        int index = ArrayUtils.indexOf(values, getValue());
        if (--index < 0)
            index = values.length - 1;
        return values[index];
    }

    @Override
    public final T[] getElements() {
        return this.values;
    }
}
