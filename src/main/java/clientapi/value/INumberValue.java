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

import clientapi.value.type.number.AbstractNumberType;

/**
 * @author Brady
 * @since 9/27/2018
 */
public interface INumberValue<T extends Number> extends IValue<T> {

    /**
     * @return The minimum value of the number
     */
    T getMinimum();

    /**
     * @return The maximum value of this number
     */
    T getMaximum();

    /**
     * @return The interval of change for this number value
     */
    T getInterval();

    /**
     * Increments the value of this {@link AbstractNumberType}
     * by {@code 1/10th} the range, multiplied by the parameter.
     *
     * @param multiplier The incrementation multiplier
     */
    @SuppressWarnings("unchecked")
    default void increment(float multiplier) {
        double range = getMaximum().doubleValue() - getMinimum().doubleValue();
        this.setValue((T) (Number) (this.getValue().doubleValue() + (range / 10.0 * multiplier)));
    }

    /**
     * Decrements the value of this {@link AbstractNumberType}
     * by {@code 1/10th} the range, multiplied by the parameter.
     *
     * @param multiplier The decrementation multiplier
     */
    default void decrement(float multiplier) {
        this.increment(-multiplier);
    }
}
