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

package clientapi.lua;

import me.zero.alpine.listener.EventHook;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;

/**
 * Holder for a Lua event hook's information. Contains the script
 * that created the hook, the target event class simple name and
 * the {@code LuaFunction} that is being hooked into the event.
 *
 * @author Brady
 * @since 11/10/2017 10:58 AM
 */
public final class LuaEventHook implements EventHook<LuaValue> {

    /**
     * The script that created this {@code LuaEventHook}
     */
    private final LuaScript script;

    /**
     * The target event class's simple name
     *
     * @see Class#getSimpleName()
     */
    private final String event;

    /**
     * The unique string identifier for this hook. Identifiers
     * only have to be unique for the same event.
     */
    private final String identifier;

    /**
     * The lua function that hooks into the target event
     */
    private final LuaFunction function;

    public LuaEventHook(LuaScript script, String event, String identifier, LuaFunction function) {
        this.script = script;
        this.event = event;
        this.identifier = identifier;
        this.function = function;
    }

    /**
     * @return The script that created this {@code LuaEventHook}
     */
    public final LuaScript getScript() {
        return this.script;
    }

    /**
     * @return The target event class's simple name
     */
    public final String getEvent() {
        return this.event;
    }

    /**
     * @return The unique string identifier for this hook.
     */
    public final String getIdentifier() {
        return this.identifier;
    }

    /**
     * @param event The event
     * @return Whether or not this {@code LuaEventHook} targets the specified event
     */
    public final boolean isTargetEvent(Object event) {
        return event.getClass().getSimpleName().equals(this.event);
    }

    @Override
    public final void invoke(LuaValue event) {
        this.function.call(event);
    }
}
