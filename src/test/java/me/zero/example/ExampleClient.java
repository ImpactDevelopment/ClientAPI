package me.zero.example;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import clientapi.Client;
import clientapi.ClientAPI;
import clientapi.ClientInfo;
import clientapi.command.Command;
import clientapi.event.defaults.game.render.TextEvent;
import clientapi.event.handle.ClientHandler;
import clientapi.manage.Manager;
import clientapi.module.Module;
import me.zero.example.command.ExampleCommandManager;
import me.zero.example.mod.ExampleModManager;

/**
 * Created by Brady on 1/19/2017.
 */
public final class ExampleClient extends Client {

    private static ExampleClient instance;
    private Manager<Module> moduleManager;
    private Manager<Command> commandManager;

    public ExampleClient(ClientInfo info) {
        super(info);
        instance = this;
    }

    @Override
    public void onInit(ClientHandler handler) {
        // Init and load module manager
        moduleManager = new ExampleModManager();
        moduleManager.load();

        // Init and load command manager
        commandManager = new ExampleCommandManager();
        commandManager.load();

        // Load plugins from a defined directory
        this.loadPlugins(mc.mcDataDir + "/example/plugins/");

        ClientAPI.EVENT_BUS.subscribe(new Object() {
            @EventHandler
            private final Listener<TextEvent> textEventListener = new Listener<>(event -> {
                if (event.getText().contains("Singleplayer")) {
                    event.setText(event.getText().replace("Singleplayer", "Singleplayer"));
                }
            });
        });
    }

    public final String getName() {
        return this.info.getName();
    }

    public final double getVersion() {
        return this.info.getBuild();
    }

    public final Manager<Module> getModuleManager() {
        return this.moduleManager;
    }

    public final Manager<Command> getCommandManager() {
        return this.commandManager;
    }

    public static ExampleClient getInstance() {
        return instance;
    }
}
