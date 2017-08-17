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
import clientapi.util.interfaces.Nameable;

import java.lang.reflect.Field;

/**
 * The implementation of IValue
 *
 * @see IValue
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
 */
public class Value<T> implements IValue<T>, Nameable {

    /**
     * Name of the Value
     */
    private final String name;

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

    public Value(String name, String id, String description, Object object, Field field) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.object = object;
        this.field = field;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getValue() {
        return (T) ReflectionUtils.getField(object, field);
    }

    @Override
    public void setValue(T value) {
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

    public final String getId() {
        return this.id;
    }
}
