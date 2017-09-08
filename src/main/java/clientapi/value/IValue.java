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

package clientapi.value;

import clientapi.util.interfaces.Identifiable;
import clientapi.util.interfaces.Nameable;
import clientapi.value.holder.IValueHolder;

/**
 * Simple interface for Values
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
 */
public interface IValue<T> extends Nameable, Identifiable, IValueHolder {

    /**
     * @return The ID of the parent value
     */
    String getParent();

    /**
     * @return The Value of this Object
     */
    T getValue();

    /**
     * Sets the Value of this Object
     *
     * @param value The new value
     */
    void setValue(T value);
}
