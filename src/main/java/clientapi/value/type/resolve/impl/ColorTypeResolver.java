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

package clientapi.value.type.resolve.impl;

import clientapi.util.ReflectionUtils;
import clientapi.util.annotation.Label;
import clientapi.value.type.ColorType;
import clientapi.value.type.resolve.TypeResolver;

import java.lang.reflect.Field;

/**
 * Default implementation of {@code TypeResolver} used to parse {@code ColorType} fields
 *
 * @author Brady
 * @since 4/11/2018 11:27 AM
 */
public final class ColorTypeResolver implements TypeResolver<ColorType> {

    @Override
    public final ColorType resolve(Object parent, Field field) {
        Label label = field.getAnnotation(Label.class);
        Integer value = (Integer) ReflectionUtils.getField(parent, field);
        if (value == null)
            value = 0xFFFFFFFF;

        ColorType type = new ColorType(label.name(), label.parent(), label.id(), label.description(), parent, field);
        type.setValue(value);
        return type;
    }
}
