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

import clientapi.value.Value;
import clientapi.value.annotation.ColorValue;
import pw.knx.feather.structures.Color;

import java.lang.reflect.Field;

/**
 * Basic type for Color values
 *
 * @see ColorValue
 *
 * @author Brady
 * @since 4/11/2018
 */
public final class ColorType extends Value<Color> {

    public ColorType(String name, String parent, String id, String description, Object object, Field field) {
        super(name, parent, id, description, object, field);
    }
}
