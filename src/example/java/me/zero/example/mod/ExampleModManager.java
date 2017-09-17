package me.zero.example.mod;

import clientapi.ClientAPI;
import clientapi.manage.Manager;
import clientapi.module.Module;
import me.zero.example.mod.mods.*;
import org.apache.logging.log4j.Level;

/**
 * Created by Brady on 1/25/2017.
 */
public final class ExampleModManager extends Manager<Module> {

    public ExampleModManager() {
        super("Module");
    }

    @Override
    public void load() {
        ClientAPI.LOGGER.log(Level.INFO, "Loading Modules");

        // Load Modules
        this.addData(
            new Aura(),
            new Camera(),
            new Fly(),
            new Hud(),
            new Speed()
        );
    }

    @Override
    public void save() {
        // Save Modules
    }
}
