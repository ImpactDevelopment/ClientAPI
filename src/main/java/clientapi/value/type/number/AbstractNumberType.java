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

package clientapi.value.type.number;

import clientapi.util.math.MathUtils;
import clientapi.value.Value;

import java.lang.reflect.Field;

/**
 * Basic type for Number values
 *
 * @author Brady
 * @since 1/23/2017
 */
public class AbstractNumberType<T extends Number> extends Value<T> {

    /**
     * Minimum value of the number
     */
    private final T minimum;

    /**
     * Maximum value of the number
     */
    private final T maximum;

    /**
     * Interval amount for this value
     */
    private final T interval;

    public AbstractNumberType(String name, String parent, String id, String description, Object object, Field field, T minimum, T maximum, T interval) {
        super(name, parent, id, description, object, field);
        this.minimum = minimum;
        this.maximum = maximum;
        this.interval = interval;
    }

    @Override
    public final T getValue() {
        return super.getValue();
    }

    @Override
    public final void setValue(T value) {
        super.setValue(MathUtils.clamp(value, minimum, maximum));
    }

    /**
     * @return The minimum value of the number
     */
    public final T getMinimum() {
        return this.minimum;
    }

    /**
     * @return The maximum value of this number
     */
    public final T getMaximum() {
        return this.maximum;
    }

    /**
     * @return The interval of change for this value
     */
    public final T getInterval() {
        return this.interval;
    }

    /**
     * Increments the value by the number specified
     * times the number range divided by 10
     */
    @SuppressWarnings("unchecked")
    public final void increment(float multiplier) {
        double range = maximum.doubleValue() - minimum.doubleValue();
        this.setValue((T) (Number) (this.getValue().doubleValue() + (range / 10.0 * multiplier)));
    }

    /**
     * Decrement the value by the number specified
     * times the number range divided by 10
     */
    public final void decrement(float multiplier) {
        this.increment(-multiplier);
    }
}
