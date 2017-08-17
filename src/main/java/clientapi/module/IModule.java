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

package clientapi.module;

import clientapi.util.interfaces.Bindable;
import clientapi.util.interfaces.Helper;
import clientapi.util.interfaces.Toggleable;

/**
 * Base for Module
 *
 * @see Module
 *
 * @author Brady
 * @since 1/21/2017 12:00 PM
 */
interface IModule extends Helper, Toggleable, Bindable {

    /**
     * @return The type of the Module
     */
    Class<?> getType();
}
