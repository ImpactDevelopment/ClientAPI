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

package clientapi.value.type;

import clientapi.util.interfaces.Toggleable;
import clientapi.value.Value;
import clientapi.value.annotation.BooleanValue;

import java.lang.reflect.Field;

/**
 * Basic type for Boolean values
 *
 * @see BooleanValue
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
 */
public final class BooleanType extends Value<Boolean> implements Toggleable {

    public BooleanType(String name, String parent, String id, String description, Object object, Field field) {
        super(name, parent, id, description, object, field);
    }

    @Override
    public final void onEnable() {}

    @Override
    public final void onDisable() {}

    @Override
    public final void setState(boolean state) {
        this.setValue(state);

        if (this.getState()) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    @Override
    public final boolean getState() {
        return super.getValue();
    }
}
