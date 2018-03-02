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

import clientapi.ClientAPI;
import clientapi.util.io.Keybind;

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
     * Description of the mode
     */
    private final String description;

    /**
     * The Keybind of this Module
     */
    private Keybind bind;

    /**
     * The state of the mode
     */
    private boolean state;

    public ModuleMode(T parent, String name, String description) {
        this.parent = parent;
        this.name = name;
        this.description = description;

        this.bind = new Keybind(Keybind.Type.TOGGLE, 0, type -> {
            if (type == Keybind.Action.CLICK) {
                this.toggle();
            }
        });
    }

    @Override
    public void toggle() {
        this.setState(!this.getState());
    }

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
     * @return The parent module
     */
    public final Module getParent() {
        return this.parent;
    }

    /**
     * @return The name of the mode
     */
    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public final boolean getState() {
        return state;
    }

    @Override
    public final Keybind getBind() {
        return this.bind;
    }

    @Override
    public final Class<?> getType() {
        return parent.getClass();
    }
}
