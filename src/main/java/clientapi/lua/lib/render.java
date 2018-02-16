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

import clientapi.lua.LuaLibrary;
import clientapi.lua.extension.FourArgFunction;
import net.minecraft.client.gui.FontRenderer;
import org.luaj.vm2.LuaInteger;
import org.luaj.vm2.LuaValue;

import java.util.Map;

import static clientapi.util.interfaces.Helper.mc;

/**
 * @author Brady
 * @since 11/8/2017 10:42 PM
 */
public final class render extends LuaLibrary {

    public render() {
        super("render");
    }

    @Override
    public final void load(Map<String, LuaValue> table) {
        table.put("DrawText", new DrawText());
        table.put("DrawTextWithShadow", new DrawTextWithShadow());
    }

    /**
     * @see FontRenderer#drawString(String, float, float, int, boolean)
     */
    private static final class DrawText extends FourArgFunction {

        @Override
        public final LuaValue call(LuaValue text, LuaValue x, LuaValue y, LuaValue color) {
            if (!text.isstring() || !x.isnumber() || !y.isnumber() || !color.isnumber())
                return NIL;

            return LuaInteger.valueOf(mc.fontRenderer.drawString(text.tojstring(), x.tofloat(), y.tofloat(), color.toint(), false));
        }
    }

    /**
     * @see FontRenderer#drawString(String, float, float, int, boolean)
     */
    private static final class DrawTextWithShadow extends FourArgFunction {

        @Override
        public final LuaValue call(LuaValue text, LuaValue x, LuaValue y, LuaValue color) {
            if (!text.isstring() || !x.isnumber() || !y.isnumber() || !color.isnumber())
                return NIL;

            return LuaInteger.valueOf(mc.fontRenderer.drawString(text.tojstring(), x.tofloat(), y.tofloat(), color.toint(), true));
        }
    }
}
