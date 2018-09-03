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

package clientapi.module;

import clientapi.util.io.Keybind;
import clientapi.value.holder.ValueHolder;

/**
 * @author Brady
 * @since 9/2/2018
 */
public abstract class AbstractModule extends ValueHolder implements IModule {

    /**
     * Name of the node
     */
    protected String name;

    /**
     * Description of the node
     */
    protected String description;

    /**
     * The Keybind of this Module
     */
    protected Keybind bind;

    /**
     * The state of the module, whether it is on or off
     */
    protected boolean state;

    AbstractModule() {}

    AbstractModule(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    @Override
    public final boolean getState() {
        return this.state;
    }

    @Override
    public final Keybind getBind() {
        return this.bind;
    }
}
