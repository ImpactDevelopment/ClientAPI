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

package clientapi.module;

import clientapi.ClientAPI;
import clientapi.util.keybind.Keybind;

/**
 * A type of module intended for use as a sub-module
 *
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
public class ModuleMode<T extends Module> implements IModule {

    /**
     * Parent Module
     */
    protected final T parent;

    /**
     * Name for the mode
     */
    private final String name;

    /**
     * The state of the mode
     */
    private boolean state;

    public ModuleMode(T parent, String name) {
        this.parent = parent;
        this.name = name;
    }

    @Override
    public void toggle() {}

    @Override
    public void setState(boolean state) {
        this.state = state;
        if (state) {
            if (parent.getState()) {
                this.onEnable();
                ClientAPI.EVENT_BUS.subscribe(this);
            }
        } else {
            ClientAPI.EVENT_BUS.unsubscribe(this);
            this.onDisable();
        }
    }

    /**
     * @return The name of the mode
     */
    public final String getName() {
        return this.name;
    }

    /**
     * @return The parent module
     */
    public final Module getParent() {
        return this.parent;
    }

    @Override
    public final boolean getState() {
        return state;
    }

    /* Methods below are irrelevant to Modes */

    @Override
    public final Keybind getBind() {
        return null;
    }

    @Override
    public final Class<?> getType() {
        return parent.getClass();
    }
}
