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

package clientapi.lua.lib;

import clientapi.lua.LuaHandler;
import clientapi.lua.LuaHookManager;
import clientapi.lua.LuaLibrary;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.ThreeArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

import java.util.Map;

/**
 * @author Brady
 * @since 11/8/2017 4:26 PM
 */
public final class hook extends LuaLibrary {

    public hook() {
        super("hook");
    }

    @Override
    public final void load(Map<String, LuaValue> table) {
        table.put("Add", new Add());
        table.put("Remove", new Remove());
    }

    /**
     * Allows for the creation of event hooks in the form of Lua functions.
     *
     * @see LuaHookManager#add(String, String, LuaFunction)
     */
    private static final class Add extends ThreeArgFunction {

        @Override
        public LuaValue call(LuaValue event, LuaValue identifier, LuaValue function) {
            if (!event.isstring() || !identifier.isstring() || !function.isfunction()) {
                return FALSE;
            }
            LuaHandler.getHandler().getHookManager().add(event.tojstring(), identifier.tojstring(), (LuaFunction) function);
            return TRUE;
        }
    }

    /**
     * Allows for the creation of event hooks in the form of Lua functions.
     *
     * @see LuaHookManager#remove(String, String)
     */
    private static final class Remove extends TwoArgFunction {

        @Override
        public LuaValue call(LuaValue event, LuaValue identifier) {
            if (!event.isstring() || !identifier.isstring()) {
                return FALSE;
            }
            LuaHandler.getHandler().getHookManager().remove(event.tojstring(), identifier.tojstring());
            return TRUE;
        }
    }
}
