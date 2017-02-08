package me.zero.example.mod;

import me.zero.client.api.manage.Manager;
import me.zero.client.api.module.Module;
import me.zero.example.ExampleClient;
import me.zero.example.mod.mods.ExampleModule;

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
        this.addData(new ExampleModule());

        // Loads Modules from the discovered Plugins by the Plugin loaders
        ExampleClient.getInstance().getPlugins().forEach(plugin -> this.addData(plugin.getModules()));
    }

    @Override
    public void save() {
        // Save Modules
    }
}
