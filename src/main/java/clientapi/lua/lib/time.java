package clientapi.lua.lib;

import clientapi.lua.LuaLibrary;
import clientapi.util.ClientAPIUtils;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

import java.util.Map;

/**
 * @author Brady
 * @since 11/16/2017 12:26 PM
 */
public final class time extends LuaLibrary {

    public time() {
        super("time");
    }

    @Override
    public final void load(Map<String, LuaValue> table) {
        table.put("Wait", new Wait());
        table.put("Later", new Later());
    }

    /**
     * Sleeps the current thread for the specified length of time in milliseconds.
     *
     * @see Thread#sleep(long)
     */
    private static final class Wait extends OneArgFunction {

        @Override
        public final LuaValue call(LuaValue arg1) {
            if (!arg1.isnumber()) {
                return LuaValue.FALSE;
            }

            // Only sleep if the specified milliseconds to sleep is greater than 0
            int ms;
            if ((ms = arg1.toint()) > 0 && ClientAPIUtils.sleep(ms)) {
                return LuaValue.TRUE;
            }

            return LuaValue.FALSE;
        }
    }

    /**
     * Executes the specified {@code LuaFunction} at a later time, delay specified in milliseconds.
     */
    private static final class Later extends TwoArgFunction {

        @Override
        public final LuaValue call(LuaValue arg1, LuaValue arg2) {
            if (!arg1.isnumber() || !arg2.isfunction()) {
                return LuaValue.FALSE;
            }

            // Queue up action for later
            new Thread(() -> {
                ClientAPIUtils.sleep(arg1.toint());
                arg2.invoke();
            });

            return LuaValue.TRUE;
        }
    }
}
