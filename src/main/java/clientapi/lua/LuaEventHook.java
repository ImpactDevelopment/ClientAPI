package clientapi.lua;

import me.zero.alpine.listener.EventHook;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaValue;

/**
 * Holder for a Lua event hook's information. Contains the script
 * that created the hook, the target event class simple name and
 * the {@code LuaFunction} that is being hooked into the event.
 *
 * @author Brady
 * @since 11/10/2017 10:58 AM
 */
public final class LuaEventHook implements EventHook<LuaValue> {

    /**
     * The script that created this {@code LuaEventHook}
     */
    private final LuaScript script;

    /**
     * The target event class's simple name
     *
     * @see Class#getSimpleName()
     */
    private final String event;

    /**
     * The lua function that hooks into the target event
     */
    private final LuaFunction function;

    public LuaEventHook(LuaScript script, String event, LuaFunction function) {
        this.script = script;
        this.event = event;
        this.function = function;
    }

    /**
     * @param event The event
     * @return Whether or not this {@code LuaEventHook} targets the specified event
     */
    public final boolean isTargetEvent(Object event) {
        return event.getClass().getSimpleName().equals(this.event);
    }

    @Override
    public final void invoke(LuaValue event) {
        this.function.call(event);
    }
}
