package me.zero.client.api.module;

import me.zero.client.ClientAPI;

import java.util.ArrayList;
import java.util.List;

import static me.zero.client.ClientAPI.Stage.PRE;

/**
 * Created by Brady on 1/19/2017.
 */
public class ModuleType {

    private String name;

    public ModuleType(String name) {
        this.name = name;
    }

    private static List<ModuleType> types = new ArrayList<>();

    public static void create(String... names) {
        for (String name : names) create(name);
    }

    public static void create(String name) {
        ClientAPI.getAPI().check(PRE, "Module Type creation after Initialization");

        name = name.toLowerCase();
        if (!types.contains(name))
            types.add(new ModuleType(name));
    }

    public static ModuleType get(String name) {
        for (ModuleType type : types) if (name.equalsIgnoreCase(name)) return type;
        return null;
    }
}
