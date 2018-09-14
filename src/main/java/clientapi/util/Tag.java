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

package clientapi.util;

import clientapi.util.interfaces.Identifiable;
import clientapi.util.interfaces.Taggable;

/**
 * A tag. Refer to {@link Taggable} for usage.
 *
 * @see Taggable
 *
 * @author Brady
 * @since 11/1/2017
 */
public final class Tag<T> implements Identifiable {

    /**
     * The value of the tag
     */
    private T value;

    /**
     * The tag's id. Should be the same across tags
     * that represent the same property.
     */
    private final String id;

    public Tag(String id) {
        this(null, id);
    }

    public Tag(T initialValue, String id) {
        this.value = initialValue;
        this.id = id;
    }

    /**
     * Gets the value of this tag.
     *
     * @param <U> The type to cast the value to
     * @return The value of this tag.
     */
    @SuppressWarnings("unchecked")
    public final <U> U getValue() {
        return (U) this.value;
    }

    /**
     * Sets the value of this tag.
     *
     * @param value The new value
     * @param <U> The type to cast to the value type
     */
    @SuppressWarnings("unchecked")
    public final <U> void setValue(U value) {
        this.value = (T) value;
    }

    @Override
    public final String getID() {
        return this.id;
    }
}
