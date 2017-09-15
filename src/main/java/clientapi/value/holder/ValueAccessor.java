/*
 * Copyright 2017 ImpactDevelopment
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

/**
 * Used in direct accessing of labeled fields. Only
 * usage is in Values, where this is used as a substitute
 * to reflection calls.
 *
 * @author Brady
 * @since 9/13/2017 10:19 PM
 */
public interface ValueAccessor {

    /**
     * Returns the value of a labeled field in this
     * class with the specified id.
     *
     * @param id The id of the field
     * @return The value
     */
    Object getFieldValue(String id);

    /**
     * Sets the value of a labeled field in this
     * class with the specified id.
     *
     * @param id The id of the field
     * @param value The new field value
     */
    void setFieldValue(String id, Object value);
}
