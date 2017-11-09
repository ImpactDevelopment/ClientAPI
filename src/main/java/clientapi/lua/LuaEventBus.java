package clientapi.lua;

import clientapi.event.AbstractEventBus;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

/**
 * @author Brady
 * @since 11/8/2017 4:57 PM
 */
final class LuaEventBus extends AbstractEventBus {

    /**
     * Instance of the {@code LuaHookManager} that created this {@code LuaEventBus}
     */
    private final LuaHookManager hookManager;

    LuaEventBus(LuaHookManager hookManager) {
        this.hookManager = hookManager;
    }

    @Override
    public final void post(Object event) {
        // If the hook manager doesn't contain any hooks, ignore
        if (hookManager.getHooks().isEmpty())
            return;

        // Coerce the event to a LuaValue
        LuaValue luaEvent = CoerceJavaToLua.coerce(event);

        String eventName = event.getClass().getSimpleName();

        // Call lua hook functions
        hookManager.getHooks().values().forEach(map -> map.forEach((e, funcs) -> {
            if (e.equals(eventName)) {
                funcs.forEach(func -> func.call(luaEvent));
            }
        }));
    }
}
