package clientapi.lua;

import org.luaj.vm2.script.LuaScriptEngine;

import javax.script.CompiledScript;
import javax.script.ScriptException;

/**
 * @author Brady
 * @since 11/8/2017 2:20 PM
 */
public final class LuaScript {

    /**
     * The engine that is used to compile this script
     */
    private final LuaScriptEngine engine;

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
     * A compiled instance of this script
     */
    private CompiledScript compiled;

    /**
     * Whether or not this script can be evaluated
     */
    private boolean canEval = true;

    public LuaScript(LuaHandler handler, Type type, String code) {
        this.engine = handler.genScriptEngine();
        this.handler = handler;
        this.type = type;
        this.code = code;
    }

    /**
     * Compiles this script in preperation for evaluation
     *
     * @see LuaScript#eval
     *
     * @throws ScriptException if the script has already been compiled
     */
    public final void compile() throws ScriptException {
        if (compiled != null)
            throw new ScriptException("Script already compiled");

        compiled = engine.compile(this.code);
    }

    /**
     * Deletes the compiled script, unhooks all event hooks,
     * and allows the script to be re-evaluated. (Only once the
     * script is re-compiled).
     */
    public final void delete() {
        handler.getHookManager().detach(this);
        compiled = null;
        canEval = true;
    }

    /**
     * Evaluates/executes this script, can only be done once if
     * the {@code Type} is set to {@code HOOK}. Returns whether
     * or not the operation was a success, based on if this script
     * has already been compiled or not and if the script can be evaluated.
     *
     * @return Whether or not the operation was a success
     * @throws ScriptException if the script is unable to be evaluated.
     */
    public final boolean eval() throws ScriptException {
        if (compiled == null || !canEval)
            return false;

        // Evaluate the script
        handler.getHookManager().setCurrentScript(this);
        compiled.eval();

        // Set the new canEval flag
        canEval = type == Type.SINGLE;
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

    /**
     * @return The compiled instance of this script, {@code null} if not yet compiled
     */
    public final CompiledScript getCompiled() {
        return this.compiled;
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
