package clientapi.lua;

import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.script.LuaScriptEngine;
import org.luaj.vm2.script.LuajContext;

import javax.script.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Brady
 * @since 11/8/2017 12:22 PM
 */
public final class LuaHandler {

    /**
     * The instance of {@code LuaHandler}
     */
    private static final LuaHandler INSTANCE = new LuaHandler();

    /**
     * An Immutable List of default libraries
     */
    private static final List<String> DEFAULT_LIBS = Arrays.asList("hook", "mc", "render");

    /**
     * The manager to create the {@code LuaScriptEngines} for all {@code LuaScripts}
     */
    private ScriptEngineManager engineManager = new ScriptEngineManager();

    /**
     * Instance of {@code LuaHookManager}
     */
    private final LuaHookManager hookManager;

    private LuaHandler() {
        this.hookManager = new LuaHookManager();
    }

    /**
     * Creates a script through this {@code LuaHandler}.
     *
     * @param type The type of script.
     * @param code The raw lua source.
     * @return The created {@code LuaScript}
     */
    public final LuaScript createScript(LuaScript.Type type, String code) {
        return new LuaScript(this, type, code);
    }

    /**
     * Creates a {@code LuaScriptEngine} for a {@code LuaScript}.
     * Only to be used by the {@code LuaScript} class.
     */
    final LuaScriptEngine genScriptEngine() {
        // Create a new LuaScriptEngine
        LuaScriptEngine engine = (LuaScriptEngine) engineManager.getEngineByName("luaj");

        // Load default libraries
        LuajContext context = (LuajContext) engine.getContext();
        OneArgFunction require = (OneArgFunction) context.globals.get("require");
        DEFAULT_LIBS.forEach(lib -> require.call("clientapi.lua.lib." + lib));

        return engine;
    }

    /**
     * @return The instance of {@code LuaHookManager}
     */
    public final LuaHookManager getHookManager() {
        return this.hookManager;
    }

    /**
     * @return The instance of {@code LuaHandler}
     */
    public static LuaHandler getHandler() {
        return INSTANCE;
    }
}
