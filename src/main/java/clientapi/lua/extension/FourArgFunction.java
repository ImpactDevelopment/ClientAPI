package clientapi.lua.extension;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.*;

/**
 * Abstract base class for Java function implementations
 * that take four arguments and return one value.
 *
 * @author Brady
 * @since 11/8/2017 6:05 PM
 */
abstract public class FourArgFunction extends LibFunction {

    abstract public LuaValue call(LuaValue arg1, LuaValue arg2, LuaValue arg3, LuaValue arg4);

    /** Default constructor */
    public FourArgFunction() {
    }

    public Varargs invoke(Varargs varargs) {
        return call(varargs.arg1(),varargs.arg(2), varargs.arg(3), varargs.arg(4));
    }

}