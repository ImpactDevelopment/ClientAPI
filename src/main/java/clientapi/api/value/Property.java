/*
 * Copyright 2017 ZeroMemes
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

package clientapi.api.value;

/**
 * Properties are similar to values but are not limited to Fields.
 * Used in Nodes to handle unique properties of the Node.
 *
 * @author Brady
 * @since 2/25/2017 12:00 PM
 */
public final class Property {

    /**
     * The label of this property
     */
    private final String label;

    /**
     * The value of this property
     */
    private Object value;

    public Property(String label) {
        this.label = label;
    }

    /**
     * @return The label of this property
     */
    public final String getLabel() {
        return this.label;
    }

    /**
     * @return The value of this property
     */
    @SuppressWarnings("unchecked")
    public final <T> T getValue() {
        return (T) this.value;
    }

    /**
     * @return The value of this property as a string
     */
    public final String getString() {
        return value.toString();
    }

    /**
     * Sets the value of this property to the specified object
     *
     * @param value New value
     */
    public final void setValue(Object value) {
        this.value = value;
    }
}
