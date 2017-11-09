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
    static final class DrawText extends FourArgFunction {

        @Override
        public final LuaValue call(LuaValue string, LuaValue x, LuaValue y, LuaValue color) {
            if (!string.isstring() || !x.isnumber() || !y.isnumber() || !color.isnumber())
                return NIL;

            return LuaInteger.valueOf(mc.fontRenderer.drawString(string.tojstring(), x.tofloat(), y.tofloat(), color.toint(), false));
        }
    }

    /**
     * @see FontRenderer#drawString(String, float, float, int, boolean)
     */
    static final class DrawTextWithShadow extends FourArgFunction {

        @Override
        public final LuaValue call(LuaValue string, LuaValue x, LuaValue y, LuaValue color) {
            if (!string.isstring() || !x.isnumber() || !y.isnumber() || !color.isnumber())
                return NIL;

            return LuaInteger.valueOf(mc.fontRenderer.drawString(string.tojstring(), x.tofloat(), y.tofloat(), color.toint(), true));
        }
    }
}
