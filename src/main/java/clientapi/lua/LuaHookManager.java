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
import org.luaj.vm2.LuaFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Used to manage lua event hooking
 *
 * @author Brady
 * @since 11/8/2017 1:53 PM
 */
public final class LuaHookManager {

    /**
     * Map matching lua scripts to a list of {@code LuaEventHooks} that they created
     */
    private final Map<LuaScript, List<LuaEventHook>> hooks;

    /**
     * A child event bus that passes events posted by {@code ClientAPI#EVENT_BUS} to Lua event hooks.
     */
    private final LuaEventBus eventBus;

    /**
     * The last script that was evaluated.
     */
    private LuaScript currentScript;

    LuaHookManager() {
        this.hooks = new HashMap<>();
        this.eventBus = new LuaEventBus(this);
        allow(true);
    }

    /**
     * Adds a hook for the specified event with the specified function. The
     * last script to be evaluated is bound to the newly created {@code LuaEventHook}.
     *
     * @param event The hook event target
     * @param identifier The unique identifier
     * @param function The lua hook function
     */
    public final void add(String event, String identifier, LuaFunction function) {
        getScriptHooks(currentScript).add(new LuaEventHook(currentScript, event, identifier, function));
    }

    /**
     * Removes a {@code LuaEventHook} with the specified identifier from
     * the event hooks that target the specified event.
     *
     * @param event The hook event target
     * @param identifier The lua hook function
     */
    public final void remove(String event, String identifier) {
        getEventHooks(event).removeIf(hook -> hook.getIdentifier().equals(identifier));
    }

    /**
     * Detaches the specified {@code LuaScript's} active hooks
     *
     * @param script The script
     * @return Whether or not the hooks were able to be detached
     */
    public final boolean detach(LuaScript script) {
        return hooks.remove(script) != null;
    }

    /**
     * Returns the hooks created by the specified {@code LuaScript}
     *
     * @param script The script
     * @return The hooks
     */
    public final List<LuaEventHook> getScriptHooks(LuaScript script) {
        return hooks.computeIfAbsent(script, s -> new ArrayList<>());
    }

    /**
     * Returns the hooks targetting the specified event
     *
     * @param event The target event
     * @return The hooks
     */
    public final List<LuaEventHook> getEventHooks(String event) {
        return hooks.values().stream()
                .flatMap(List::stream)
                .filter(hook -> hook.getEvent().equals(event))
                .collect(Collectors.toList());
    }

    /**
     * @return All of the active hooks
     */
    public final Map<LuaScript, List<LuaEventHook>> getHooks() {
        return this.hooks;
    }

    /**
     * Set whether or not the {@code LuaHookManager} is allowed to pass
     * events from {@code ClientAPI#EVENT_BUS} to the lua event hooks.
     *
     * @param canPassEvents Whether or not events can be passed
     */
    public final void allow(boolean canPassEvents) {
        if (canPassEvents)
            ClientAPI.EVENT_BUS.attach(eventBus);
        else
            ClientAPI.EVENT_BUS.detach(eventBus);
    }

    /**
     * Sets the current script being evaluated. Only
     * for internal usage by {@code LuaScript} to keep
     * track of what script is having hooks registered.
     *
     * @param currentScript The current script being evaluated
     */
    void setCurrentScript(LuaScript currentScript) {
        this.currentScript = currentScript;
    }
}
