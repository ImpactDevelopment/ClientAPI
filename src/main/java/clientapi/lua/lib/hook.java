package clientapi.lua.lib;

import clientapi.lua.LuaContext;
import clientapi.lua.LuaHookManager;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

/**
 * @author Brady
 * @since 11/8/2017 4:26 PM
 */
public final class hook extends TwoArgFunction {

    @Override
    public final LuaValue call(LuaValue modname, LuaValue env) {
        LuaValue library = tableOf();

        // Setup functions
        library.set("create", new create());

        env.set("hook", library);

        return library;
    }

    /**
     * Allows for the creation of event hooks in the form of Lua functions.
     *
     * @see LuaHookManager#create(String, LuaFunction)
     */
    private static final class create extends TwoArgFunction {

        @Override
        public LuaValue call(LuaValue event, LuaValue function) {
            if (!event.isstring() || !function.isfunction()) {
                return FALSE;
            }
            LuaContext.getContext().getHookManager().create(event.tojstring(), (LuaFunction) function);
            return TRUE;
        }
    }
}
