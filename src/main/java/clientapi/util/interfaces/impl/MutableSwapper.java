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

package clientapi.util.interfaces.impl;

import clientapi.util.interfaces.Mutable;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * An implementation of {@link Mutable} used to temporarily set the value
 * of a "child" {@link Mutable} and then reset it to the original value.
 *
 * @see Mutable
 *
 * @author Brady
 * @since 5/1/2018
 */
public final class MutableSwapper<T> implements Mutable<T> {

    /**
     * {@link Mutable} used to get/set some value
     */
    private final Mutable<T> mutable;

    /**
     * The previous value that the mutable held
     */
    private T previous;

    public MutableSwapper(Consumer<T> set, Supplier<T> get) {
        this(new MergedMutable<>(set, get));
    }

    public MutableSwapper(Mutable<T> mutable) {
        this.mutable = mutable;
    }

    @Override
    public final void accept(T value) {
        previous = mutable.get();
        mutable.accept(value);
    }

    @Override
    public final T get() {
        return mutable.get();
    }

    /**
     * Resets the child {@link Mutable}'s value to the original one.
     */
    public final void reset() {
        mutable.accept(previous);
    }
}
