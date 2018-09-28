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

import clientapi.value.ICycleableValue;
import clientapi.value.Value;

import java.lang.reflect.Field;

/**
 * A value that can have a specific set of values
 *
 * @author Brady
 * @since 2/24/2017
 */
public final class MultiType extends Value<String> implements ICycleableValue<String> {

    /**
     * Different values
     */
    private final String[] values;

    public MultiType(String name, String parent, String id, String description, Object object, Field field, String[] values) {
        super(name, parent, id, description, object, field);
        this.values = values;
        this.setValue(values[0]);
    }

    @Override
    public final String[] getElements() {
        return this.values;
    }
}
