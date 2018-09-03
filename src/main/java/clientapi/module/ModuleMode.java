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
import org.lwjgl.input.Keyboard;

/**
 * A type of module intended for use as a sub-module
 *
 * @author Brady
 * @since 2/24/2017
 */
public class ModuleMode<T extends Module> extends AbstractModule {

    /**
     * Parent Module
     */
    protected final T parent;

    public ModuleMode(T parent, String name, String description) {
        super(name, description);
        this.parent = parent;

        this.bind = new Keybind(Keybind.Type.TOGGLE, Keyboard.KEY_NONE, type -> {
            if (type == Keybind.Action.CLICK) {
                parent.setState(parent.getMode() != this || !parent.getState());
                parent.setMode(this);
            }
        });
    }

    @Override
    public final void setState(boolean state) {
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

    @Override
    public final Class<?> getType() {
        return this.parent.getClass();
    }
}
