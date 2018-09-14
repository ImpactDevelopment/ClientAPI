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

package clientapi.util.interfaces.impl;

import clientapi.util.ReflectionUtils;
import clientapi.util.interfaces.Mutable;

import java.lang.reflect.Field;

/**
 * {@link Mutable} that has access over a field via the Reflection API
 *
 * @author Brady
 * @since 5/1/2018
 */
public class MutableField<T> extends MergedMutable<T> {

    public MutableField(Object object, String field) {
        this(object, ReflectionUtils.findField(object, field));
    }

    public MutableField(Object object, Class<?> clazz, String field) {
        this(object, ReflectionUtils.findField(clazz, field));
    }

    @SuppressWarnings("unchecked")
    public MutableField(Object object, Field field) {
        super(
                value -> ReflectionUtils.setField(object, field, value),
                () -> (T) ReflectionUtils.getField(object, field)
        );
    }
}
