package clientapi.lua;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.TwoArgFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brady
 * @since 11/8/2017 10:25 PM
 */
public abstract class LuaLibrary extends TwoArgFunction {

    /**
     * The identifier for the {@code LuaTable} reference.
     */
    private final String key;

    public LuaLibrary(String key) {
        this.key = key;
    }

    @Override
    public final LuaValue call(LuaValue modname, LuaValue env) {
        LuaValue library = tableOf();

        // Load library table
        Map<String, LuaValue> table = new HashMap<>();
        load(table);
        table.forEach(library::set);

        env.set(key, library);
        return library;
    }

    /**
     * Loads the library value table by passing a {@code Map}
     * with the generic parameters {@code String} and {@code LuaValue},
     * where string represents the identifier it's paired value.
     *
     * @param table The library value table
     */
    public abstract void load(Map<String, LuaValue> table);
}
