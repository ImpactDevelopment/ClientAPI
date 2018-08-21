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

import clientapi.util.annotation.Label;
import clientapi.value.type.VoidType;
import clientapi.value.type.resolve.TypeResolver;

import java.lang.reflect.Field;

/**
 * @author Brady
 * @since 8/21/2018
 */
public final class VoidTypeResolver implements TypeResolver<VoidType> {

    @Override
    public final VoidType resolve(Object parent, Field field) {
        Label label = field.getAnnotation(Label.class);
        VoidType type = new VoidType(label.name(), label.parent(), label.id(), label.description(), parent, field);
        type.setValue(null);
        return type;
    }
}
