package me.zero.client.api.module;

import me.zero.client.load.ClientAPI;

import java.util.ArrayList;
import java.util.List;

import static me.zero.client.load.ClientAPI.Stage.PRE;

/**
 * Module Type, used to categorize modules
 *
 * @see me.zero.client.api.module.Module
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public class ModuleType {

    /**
     * Name of Type
     */
    private String name;

    private ModuleType(String name) {
        this.name = name;
    }

    /**
     * Stores module types
     */
    private static List<ModuleType> types = new ArrayList<>();

    /**
     * Creates multiple ModuleTypes
     *
     * @see #create(String)
     *
     * @since 1.0
     *
     * @param names
     */
    public static void create(String... names) {
        for (String name : names) create(name);
    }

    /**
     * Creates a Module Type.
     * Throws an ActionNotSupportedException if called after Pre-Init.
     *
     * @see #create(String...)
     *
     * @since 1.0
     *
     * @param name
     */
    public static void create(String name) {
        ClientAPI.getAPI().check(PRE, "Module Type creation after Pre-Initialization");

        name = name.toLowerCase();
        for (ModuleType type : types) {
            if (type.name.equalsIgnoreCase(name)) return;
        }

        types.add(new ModuleType(name));
    }

    /**
     * Returns module types
     *
     * @since 1.0
     */
    public static List<ModuleType> values() {
        return types;
    }

    /**
     * Gets a Module Type by name
     *
     * @since 1.0
     */
    public static ModuleType get(String name) {
        for (ModuleType type : types) if (name.equalsIgnoreCase(name)) return type;
        return null;
    }
}
