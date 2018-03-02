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

package clientapi.lua.lib;

import clientapi.lua.LuaLibrary;
import net.minecraft.client.entity.EntityPlayerSP;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;

import java.util.Map;

import static clientapi.util.interfaces.Helper.mc;

/**
 * @author Brady
 * @since 11/8/2017 12:26 PM
 */
public final class mc extends LuaLibrary {

    public mc() {
        super("mc");
    }

    @Override
    public final void load(Map<String, LuaValue> table) {
        table.put("SendChat", new SendChat());
    }

    /**
     * @see EntityPlayerSP#sendChatMessage(String)
     */
    private static final class SendChat extends OneArgFunction {

        @Override
        public final LuaValue call(LuaValue message) {
            if (message.isstring() && mc.player != null) {
                mc.player.sendChatMessage(message.tojstring());
                return TRUE;
            }
            return FALSE;
        }
    }
}
