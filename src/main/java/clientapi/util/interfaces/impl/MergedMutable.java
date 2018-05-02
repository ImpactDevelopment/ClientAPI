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
 * A mutable that is constructed from merging a {@code Consumer} and {@code Supplier}.
 *
 * @author Brady
 * @since 5/1/2018 6:51 PM
 */
public class MergedMutable<T> implements Mutable<T> {

    /**
     * The consumer to set some value
     */
    private final Consumer<T> set;

    /**
     * The supplier to provide some value
     */
    private final Supplier<T> get;

    public MergedMutable(Consumer<T> set, Supplier<T> get) {
        this.set = set;
        this.get = get;
    }

    @Override
    public final void accept(T value) {
        this.set.accept(value);
    }

    @Override
    public final T get() {
        return this.get.get();
    }
}
