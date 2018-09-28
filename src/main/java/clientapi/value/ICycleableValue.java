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

import clientapi.util.interfaces.Cycleable;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author Brady
 * @since 9/27/2018
 */
public interface ICycleableValue<T> extends IValue<T>, Cycleable<T> {

    @Override
    default T current() {
        return this.getValue();
    }

    @Override
    default T next() {
        T value = peekNext();
        this.setValue(value);
        return value;
    }

    @Override
    default T last() {
        T value = peekLast();
        this.setValue(value);
        return value;
    }

    @Override
    default T peekNext() {
        T[] values = getElements();
        int index = ArrayUtils.indexOf(values, getValue());
        if (++index >= values.length)
            index = 0;
        return values[index];
    }

    @Override
    default T peekLast() {
        T[] values = getElements();
        int index = ArrayUtils.indexOf(values, getValue());
        if (--index < 0)
            index = values.length - 1;
        return values[index];
    }
}
