package clientapi.lua.extension;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.LibFunction;

/**
 * Abstract base class for Java function implementations
 * that take five arguments and return one value.
 *
 * @author Brady
 * @since 11/8/2017 6:05 PM
 */
public abstract class FiveArgFunction extends LibFunction {

    public abstract LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3, LuaValue arg4, LuaValue arg5);

    /** Default constructor */
    public FiveArgFunction() {
    }

    public final Varargs invoke(Varargs varargs) {
        return call(varargs.arg1(), varargs.arg(2), varargs.arg(3), varargs.arg(4), varargs.arg(5));
    }
}
