package me.zero.example.mod;

import clientapi.api.manage.Manager;
import clientapi.api.module.Module;
import clientapi.api.util.logger.Level;
import clientapi.api.util.logger.Logger;
import me.zero.example.ExampleClient;
import me.zero.example.mod.mods.*;

/**
 * Created by Brady on 1/25/2017.
 */
public final class ExampleModManager extends Manager<Module> {

    public ExampleModManager() {
        super("Module");
    }

    @Override
    public void load() {
        Logger.instance.log(Level.INFO, "Loading Modules");

        // Load Modules
        this.addData(
            new Aura(),
            new Camera(),
            new Fly(),
            new Hud(),
            new Speed()
        );

        // Loads Modules from the discovered Plugins by the Plugin loaders
        ExampleClient.getInstance().getPlugins().forEach(plugin -> this.addData(plugin.getModules()));
    }

    @Override
    public void save() {
        // Save Modules
    }
}
