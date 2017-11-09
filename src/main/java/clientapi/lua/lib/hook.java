package clientapi.lua.lib;

import clientapi.lua.LuaHandler;
import clientapi.lua.LuaHookManager;
import clientapi.lua.LuaLibrary;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
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
    }

    /**
     * Allows for the creation of event hooks in the form of Lua functions.
     *
     * @see LuaHookManager#create(String, LuaFunction)
     */
    private static final class Add extends TwoArgFunction {

        @Override
        public LuaValue call(LuaValue event, LuaValue function) {
            if (!event.isstring() || !function.isfunction()) {
                return FALSE;
            }
            LuaHandler.getHandler().getHookManager().create(event.tojstring(), (LuaFunction) function);
            return TRUE;
        }
    }
}
