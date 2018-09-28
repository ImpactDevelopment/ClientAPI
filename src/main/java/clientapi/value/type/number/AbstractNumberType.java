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
import clientapi.value.INumberValue;
import clientapi.value.Value;

import java.lang.reflect.Field;
import java.util.function.Function;

/**
 * Basic type for Number values
 *
 * @author Brady
 * @since 1/23/2017
 */
public class AbstractNumberType<T extends Number> extends Value<T> implements INumberValue<T> {

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

    /**
     * The function that is called in {@link Number} to yield this Number Type's value
     */
    private final Function<Number, T> valueFunction;

    public AbstractNumberType(String name, String parent, String id, String description, Object object, Field field, T minimum, T maximum, T interval, Function<Number, T> valueFunction) {
        super(name, parent, id, description, object, field);
        this.minimum = minimum;
        this.maximum = maximum;
        this.interval = interval;
        this.valueFunction = valueFunction;
    }

    @Override
    public final T getValue() {
        return super.getValue();
    }

    @Override
    public final void setValue(T value) {
        super.setValue(MathUtils.clamp(valueFunction.apply(value), minimum, maximum));
    }

    @Override
    public final T getMinimum() {
        return this.minimum;
    }

    @Override
    public final T getMaximum() {
        return this.maximum;
    }

    @Override
    public final T getInterval() {
        return this.interval;
    }
}
