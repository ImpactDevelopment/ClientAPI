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

package clientapi.lua;

import clientapi.ClientAPI;
import clientapi.event.AbstractEventBus;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

/**
 * Used as a child event bus to hook into all events coming
 * from the ClientAPI event bus
 *
 * @see ClientAPI#EVENT_BUS
 *
 * @author Brady
 * @since 11/8/2017 4:57 PM
 */
final class LuaEventBus extends AbstractEventBus {

    /**
     * Instance of the {@code LuaHookManager} that created this {@code LuaEventBus}
     */
    private final LuaHookManager hookManager;

    LuaEventBus(LuaHookManager hookManager) {
        this.hookManager = hookManager;
    }

    @Override
    public final void post(Object event) {
        // If the hook manager doesn't contain any hooks, ignore
        if (hookManager.getHooks().isEmpty())
            return;

        // Coerce the event to a LuaValue
        LuaValue luaEvent = CoerceJavaToLua.coerce(event);

        // Call lua hook functions
        hookManager.getHooks().values().forEach(hooks -> hooks.forEach(hook -> {
            if (hook.isTargetEvent(event)) {
                hook.invoke(luaEvent);
            }
        }));
    }
}
