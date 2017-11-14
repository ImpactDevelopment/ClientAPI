package clientapi.lua;

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
