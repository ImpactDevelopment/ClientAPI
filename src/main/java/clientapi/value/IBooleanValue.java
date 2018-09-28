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

package clientapi.value;

import clientapi.util.interfaces.Toggleable;

/**
 * @author Brady
 * @since 9/27/2018
 */
public interface IBooleanValue extends IValue<Boolean>, Toggleable {

    @Override
    default void setState(boolean state) {
        this.setValue(state);

        if (this.getState()) {
            this.onEnable();
        } else {
            this.onDisable();
        }
    }

    @Override
    default boolean getState() {
        return this.getValue();
    }
}
