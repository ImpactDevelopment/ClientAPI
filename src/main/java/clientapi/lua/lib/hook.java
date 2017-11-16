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
