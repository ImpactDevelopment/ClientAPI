/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.lua;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

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

    LuaScript(LuaHandler handler, String code) {
        this.handler = handler;
        this.code = code;
    }

    /**
     * Compiles this script in preperation for evaluation
     *
     * @see LuaScript#exec()
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
    final void delete() {
        handler.getHookManager().detach(this);
        globals = null;
        compiled = null;
    }

    /**
     * Executes this script. Returns whether or not the operation
     * was a success, based on if this script has already been
     * compiled or not and if the script can be executed.
     *
     * @return Whether or not the operation was a success
     * @throws ScriptException if the script is unable to be executed.
     */
    public final boolean exec() throws ScriptException {
        if (compiled == null)
            return false;

        // Evaluate the script
        handler.getHookManager().setCurrentScript(this);
        compiled.call();
        return true;
    }

    /**
     * @return The raw lua code for this script
     */
    public final String getCode() {
        return this.code;
    }
}
