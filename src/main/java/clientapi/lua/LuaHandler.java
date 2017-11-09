package clientapi.lua;

import clientapi.util.interfaces.Loadable;
import org.luaj.vm2.script.LuaScriptEngine;

import javax.script.*;

/**
 * @author Brady
 * @since 11/8/2017 12:22 PM
 */
public final class LuaHandler implements Loadable {

    /**
     * Instance of the LuaJ script engine. Created when {@code LuaHandler#load()}
     * is called. Used to compile and evaluate lua scripts.
     */
    private LuaScriptEngine engine;

    LuaHandler() {}

    @Override
    public final void load() {
        // Setup the script engine
        this.engine = (LuaScriptEngine) new ScriptEngineManager().getEngineByName("luaj");

        // Setup the script bindings
        Bindings bindings = engine.createBindings();
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
    }

    /**
     * @return The {@code LuaScriptEngine} being used by this {@code LuaHandler}
     */
    public final LuaScriptEngine getEngine() {
        return this.engine;
    }
}
