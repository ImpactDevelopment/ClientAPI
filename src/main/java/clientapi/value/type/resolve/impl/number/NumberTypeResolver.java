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

package clientapi.value.type.resolve.impl.number;

import clientapi.util.annotation.Label;
import clientapi.value.type.number.AbstractNumberType;
import clientapi.value.type.resolve.TypeResolver;

import java.lang.reflect.Field;

/**
 * @author Brady
 * @since 9/1/2018
 */
public interface NumberTypeResolver<T extends AbstractNumberType<V>, V extends Number> extends TypeResolver<T> {

    default T resolve0(NumberTypeProvider<T, V> provider, boolean primitive, Object parent, Field field, V minimum, V maximum, V interval) {
        Label label = field.getAnnotation(Label.class);

        T type = provider.get(
                label.name(),
                label.parent(),
                label.id(),
                label.description(),

                parent,
                field,

                minimum,
                maximum,
                interval
        );

        if (primitive) {
            if (type.getValue().equals(0))
                type.setValue(type.getMinimum());
        } else {
            if (type.getValue() == null)
                type.setValue(type.getMinimum());
        }

        return type;
    }

    interface NumberTypeProvider<T extends AbstractNumberType<V>, V extends Number> {

        T get(String name, String parent, String id, String description, Object object, Field field, V minimum, V maximum, V interval);
    }
}
