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

import clientapi.util.interfaces.Describable;
import clientapi.util.interfaces.Identifiable;
import clientapi.util.interfaces.Nameable;
import clientapi.value.holder.IValueHolder;
import me.zero.alpine.event.EventState;

import java.util.stream.Stream;

/**
 * Simple interface for Values
 *
 * @author Brady
 * @since 1/23/2017
 */
public interface IValue<T> extends Nameable, Describable, Identifiable, IValueHolder {

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

    /**
     * Adds all of the specified change listeners, defaulting to EventState.PRE
     *
     * @param listeners The change listeners
     */
    default void addAllChangeListeners(Iterable<ValueChangeListener<T>> listeners) {
        this.addAllChangeListeners(EventState.PRE, listeners);
    }

    /**
     * Adds all of the specified change listeners
     *
     * @param state The state of the change, either before or after it happened
     * @param listeners The change listeners
     */
    default void addAllChangeListeners(EventState state, Iterable<ValueChangeListener<T>> listeners) {
        listeners.forEach(listener -> this.addChangeListener(state, listener));
    }

    /**
     * Adds a change listener to this value, defaulting to EventState.PRE
     *
     * @param listener The change listener
     */
    default void addChangeListener(ValueChangeListener<T> listener) {
        this.addChangeListener(EventState.PRE, listener);
    }

    /**
     * Adds a change listener to this value
     *
     * @param state The state of the change, either before or after it happened
     * @param listener The change listener
     */
    void addChangeListener(EventState state, ValueChangeListener<T> listener);

    /**
     * Intended to be used with flatMap() to flatten the IValue tree recursively
     * @param value the root node of the tree
     * @return the flattened tree as a stream
     */
    static Stream<IValue> flatten(IValue value) {
        return Stream.concat(
                Stream.of(value),
                value.getValues().stream().flatMap(IValue::flatten));
    }
}
