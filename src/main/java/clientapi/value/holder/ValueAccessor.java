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

package clientapi.value.holder;

import clientapi.load.transform.impl.ValueAccessorTransformer;
import clientapi.util.interfaces.Mutable;
import clientapi.util.interfaces.impl.MergedMutable;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Used in direct accessing of labeled fields. Only
 * usage is in Values, where this is used as a substitute
 * to reflection calls.
 *
 * @see ValueAccessorTransformer
 *
 * @author Brady
 * @since 9/13/2017
 */
public interface ValueAccessor {

    /**
     * Returns a supplier that can be used to get
     * the value of the target field. (By field name)
     *
     * @param field The name of the field
     * @return The supplier "getter"
     */
    Supplier<Object> getFieldGetter(String field);

    /**
     * Returns a consumer that can be used to set the
     * value of the target field. (By field name)
     *
     * @param field The name of the field
     * @return The consumer "setter"
     */
    Consumer<Object> getFieldSetter(String field);

    /**
     * Returns a mutable comprised of the specified field's getters and setters,
     * which are provided by {@link ValueAccessor#getFieldSetter(String)} and
     * {@link ValueAccessor#getFieldGetter(String)}.
     *
     * @param field The name of the field
     * @return The mutable
     */
    default Mutable<Object> getFieldMutable(String field) {
        return new MergedMutable<>(getFieldSetter(field), getFieldGetter(field));
    }
}
