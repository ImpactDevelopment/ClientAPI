package me.zero.example;

import me.zero.client.api.manage.Manager;
import me.zero.client.api.module.Module;

/**
 * Created by Brady on 1/25/2017.
 */
public class ExampleModManager extends Manager<Module> {

    public ExampleModManager() {
        super("Module");
    }

    @Override
    public void load() {
        // Load Modules
    }

    @Override
    public void save() {
        // Save Modules
    }
}
