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

package clientapi.value.type.resolve.impl;

import clientapi.util.annotation.Label;
import clientapi.value.annotation.MultiValue;
import clientapi.value.type.MultiType;
import clientapi.value.type.resolve.TypeResolver;

import java.lang.reflect.Field;

/**
 * Default implementation of {@code TypeResolver} used to parse {@code MultiType} fields
 *
 * @author Brady
 * @since 2/8/2018 3:24 PM
 */
public final class MultiTypeResolver implements TypeResolver<MultiType> {

    @Override
    public final MultiType resolve(Object parent, Field field) {
        Label label = field.getAnnotation(Label.class);
        MultiValue multi = field.getAnnotation(MultiValue.class);

        MultiType type = new MultiType(label.name(), label.parent(), label.id(), label.description(), parent, field, multi.value());
        if (type.getValue() == null)
            type.setValue(type.getMultiValues()[0]);

        return type;
    }
}
