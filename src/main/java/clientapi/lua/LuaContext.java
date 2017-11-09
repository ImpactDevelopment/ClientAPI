package clientapi.lua;

/**
 * Defines instances of the classes required for Lua functionality.
 *
 * @see LuaHandler
 * @see LuaHookManager
 *
 * @author Brady
 * @since 11/8/2017 4:45 PM
 */
public final class LuaContext {

    /**
     * The global {@code LuaContext} instance
     */
    private static final LuaContext CONTEXT = new LuaContext();

    /**
     * Instance of {@code LuaHandler}
     */
    private final LuaHandler handler;

    /**
     * Instance of {@code LuaHookManager}
     */
    private final LuaHookManager hookManager;

    private LuaContext() {
        this.handler = new LuaHandler();
        this.hookManager = new LuaHookManager();
    }

    /**
     * Creates a script through this {@code LuaContext}.
     *
     * @param type The type of script.
     * @param code The raw lua source.
     * @return The created {@code LuaScript}
     */
    public final LuaScript createScript(LuaScript.Type type, String code) {
        return new LuaScript(this, type, code);
    }

    /**
     * @return The instance of {@code LuaHandler}
     */
    public final LuaHandler getHandler() {
        return this.handler;
    }

    /**
     * @return The instance of {@code LuaHookManager}
     */
    public final LuaHookManager getHookManager() {
        return this.hookManager;
    }

    /**
     * @return The instance of the global lua context
     */
    public static LuaContext getContext() {
        return CONTEXT;
    }
}
