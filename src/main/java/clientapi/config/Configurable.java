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

package clientapi.config;

import clientapi.util.interfaces.Initializable;

/**
 * A generic configurable type that has a default constructor
 * taking in the generic configuration type.
 *
 * @see JsonConfiguration
 * @see ClientConfiguration
 *
 * @author Brady
 * @since 9/1/2017
 */
public abstract class Configurable<T extends JsonConfiguration> implements Initializable {

    /**
     * The configuration that was provided in the instantiation of this object
     */
    protected T config;

    public Configurable(T config) {
        this.config = config;
    }

    /**
     * Returns the configuration that was used to instantiate
     * this object, contains basic information.
     *
     * @return The configuration
     */
    public final T getConfig() {
        return this.config;
    }
}
