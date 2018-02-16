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
import clientapi.value.annotation.NumberValue;
import clientapi.value.type.NumberType;
import clientapi.value.type.resolve.TypeResolver;

import java.lang.reflect.Field;

/**
 * Default implementation of {@code TypeResolver} used to parse {@code NumberType} fields
 *
 * @author Brady
 * @since 2/8/2018 3:27 PM
 */
public final class NumberTypeResolver implements TypeResolver<NumberType> {

    @Override
    public final NumberType resolve(Object parent, Field field) {
        Label label = field.getAnnotation(Label.class);
        NumberValue num = field.getAnnotation(NumberValue.class);

        // This is an absolute mess, still finding some other solution
        NumberType type = null;
        if (field.getType() == Byte.class || field.getType() == Byte.TYPE) {
            type = new NumberType<Byte>(label.name(), label.parent(), label.id(), label.description(), parent, field, (byte) num.min(), (byte) num.max(), (byte) num.interval()) { };
        } else if (field.getType() == Short.class || field.getType() == Short.TYPE) {
            type = new NumberType<Short>(label.name(), label.parent(), label.id(), label.description(), parent, field, (short) num.min(), (short) num.max(), (short) num.interval()) { };
        } else if (field.getType() == Integer.class || field.getType() == Integer.TYPE) {
            type = new NumberType<Integer>(label.name(), label.parent(), label.id(), label.description(), parent, field, (int) num.min(), (int) num.max(), (int) num.interval()) { };
        } else if (field.getType() == Long.class || field.getType() == Long.TYPE) {
            type = new NumberType<Long>(label.name(), label.parent(), label.id(), label.description(), parent, field, (long) num.min(), (long) num.max(), (long) num.interval()) { };
        } else if (field.getType() == Float.class || field.getType() == Float.TYPE) {
            type = new NumberType<Float>(label.name(), label.parent(), label.id(), label.description(), parent, field, (float) num.min(), (float) num.max(), (float) num.interval()) { };
        } else if (field.getType() == Double.class || field.getType() == Double.TYPE) {
            type = new NumberType<Double>(label.name(), label.parent(), label.id(), label.description(), parent, field, num.min(), num.max(), num.interval()) { };
        }

        if (type == null)
            return null;

        if (field.getType().isPrimitive()) {
            if (type.getValue().equals(0))
                type.setValue(type.getMinimum());
        } else {
            if (type.getValue() == null)
                type.setValue(type.getMinimum());
        }

        return type;
    }
}
