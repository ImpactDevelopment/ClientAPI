/*
 * Copyright 2017 ZeroMemes
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

package clientapi.api.value.holder;

import clientapi.api.value.Value;

import java.util.Collection;

/**
 * The "shell" for a value holder
 *
 * @author Brady
 * @since 5/12/2017 2:56 PM
 */
public interface IValueHolder {

    /**
     * Adds a value to this holder, should
     * only be added if there isn't already
     * a value with the same id.
     *
     * @param value Value being added
     * @return true if the value was able to be added
     */
    boolean addValue(Value value);

    /**
     * Gets the value that this holder may
     * or may not hold, from an id.
     *
     * @param id The id of the target value
     * @return The value, if it is found, otherwise null
     */
    Value getValue(String id);

    /**
     * @return All of the values that this holder "holds"
     */
    Collection<Value> getValues();
}
