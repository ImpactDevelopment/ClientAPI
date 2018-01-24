package clientapi.lua;

import java.util.ArrayList;
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
     * Instance of {@code LuaHookManager}
     */
    private final LuaHookManager hookManager;

    /**
     * A list containing all scripts created by this {@code LuaHandler}
     */
    private final List<LuaScript> scripts = new ArrayList<>();

    private LuaHandler() {
        this.hookManager = new LuaHookManager();
    }

    /**
     * Creates a script through this {@code LuaHandler}.
     *
     * @param code The raw lua source.
     * @return The created {@code LuaScript}
     */
    public final LuaScript createScript(String code) {
        LuaScript script = new LuaScript(this, code);
        this.scripts.add(script);
        return script;
    }

    /**
     * Deletes the specified script and removes it from this handler.
     *
     * @param script The script to release
     * @return Whether or not the specified script was released
     */
    public final boolean release(LuaScript script) {
        if (!scripts.contains(script))
            return false;

        scripts.remove(script);
        script.delete();

        return true;
    }
    /**
     * @return The scripts that have been created by this {@code LuaHandler}
     */
    public final List<LuaScript> getScripts() {
        return this.scripts;
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
