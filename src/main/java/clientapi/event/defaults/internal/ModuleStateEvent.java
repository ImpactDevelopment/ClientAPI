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

package clientapi.event.defaults.internal;

import clientapi.module.Module;
import me.zero.alpine.type.Cancellable;

/**
 * Called when a Module's state is changed. If the
 * event is cancelled, the state is not changed.
 *
 * @see Module#setState(boolean)
 *
 * @author Brady
 * @since 4/5/2017 12:00 PM
 */
public final class ModuleStateEvent extends Cancellable {

    /**
     * Module having a change in state
     */
    private final Module module;

    /**
     * New state of the module
     */
    private final boolean newState;

    public ModuleStateEvent(Module module, boolean newState) {
        this.module = module;
        this.newState = newState;
    }

    /**
     * @return The module having a change in state
     */
    public final Module getModule() {
        return this.module;
    }

    /**
     * @return The new state of the Module
     */
    public final boolean getNewState() {
        return this.newState;
    }
}
