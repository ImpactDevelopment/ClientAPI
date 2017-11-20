package clientapi.lua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import javax.script.CompiledScript;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Brady
 * @since 11/8/2017 2:20 PM
 */
public final class LuaScript {

    /**
     * An Immutable List of default libraries
     */
    private static final List<String> DEFAULT_LIBS = Arrays.asList("hook", "mc", "render", "time");

    /**
     * {@code LuaHandler} that created this {@code LuaScript}
     */
    private final LuaHandler handler;

    /**
     * The type of script
     */
    private final Type type;

    /**
     * The raw lua code
     */
    private final String code;

    /**
     * The scope for this script
     */
    private Globals globals;

    /**
     * The compiled script
     */
    private LuaValue compiled;

    /**
     * Whether or not this script can be executed
     */
    private boolean canExec = true;

    public LuaScript(LuaHandler handler, Type type, String code) {
        this.handler = handler;
        this.type = type;
        this.code = code;
    }

    /**
     * Compiles this script in preperation for evaluation
     *
     * @see LuaScript#exec
     *
     * @throws ScriptException if the script has already been compiled
     */
    public final void compile() throws ScriptException {
        // Create globals
        globals = JsePlatform.standardGlobals();

        // Load libs
        LuaValue require = globals.get("require");
        DEFAULT_LIBS.forEach(lib -> require.call("clientapi.lua.lib." + lib));

        // Compile script
        compiled = globals.load(code);
    }

    /**
     * Deletes the compiled script, unhooks all event hooks,
     * and allows the script to be re-evaluated. (Only once the
     * script is re-compiled).
     */
    public final void delete() {
        handler.getHookManager().detach(this);
        globals = null;
        compiled = null;
        canExec = true;
    }

    /**
     * Executes this script, can only be done once if the {@code Type}
     * is set to {@code HOOK}. If the script is type {@code SINGLE},
     * the script should be run on a separate thread in the case that
     * {@code time.Wait(int)} calls are made to sleep the execution
     * thread. Returns whether or not the operation was a success,
     * based on if this script has already been compiled or not and
     * if the script can be executed.
     *
     * @return Whether or not the operation was a success
     * @throws ScriptException if the script is unable to be executed.
     */
    public final boolean exec() throws ScriptException {
        if (compiled == null || !canExec)
            return false;

        // Evaluate the script
        handler.getHookManager().setCurrentScript(this);
        compiled.call();

        // Set the new canEval flag
        canExec = type == Type.SINGLE;
        return true;
    }

    /**
     * @return The type of script
     */
    public final Type getType() {
        return this.type;
    }

    /**
     * @return The raw lua code for this script
     */
    public final String getCode() {
        return this.code;
    }

    public enum Type {
        /**
         * Type of script that is intended to only be executed
         * a single time, triggered by some event. For example,
         * "macro" type scripts that would
         */
        SINGLE,

        /**
         * Type of script that is intended to be executed once to
         * create event hook functions and have them invoked as long
         * as the script is active.
         */
        HOOK
    }
}
