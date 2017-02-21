package me.zero.example;

import me.zero.client.api.Client;
import me.zero.client.api.ClientInfo;
import me.zero.client.api.command.CommandHandler;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.util.factory.AuthenticationFactory;
import me.zero.example.command.CommandManager;
import me.zero.example.mod.ExampleModManager;

/**
 * Created by Brady on 1/19/2017.
 */
public class ExampleClient extends Client {

    private static ExampleClient instance;
    private ClientInfo info;

    public ExampleClient() {
        instance = this;
    }

    @Override
    public void onInit(ClientInfo info) {
        /*
        AuthenticationFactory.create().username("example@host.xyz").password("12345").login(); // Login to our account
        */

        this.info = info;                                // Gets the client info for later usage
        this.setModuleManager(new ExampleModManager());  // Create Module Manager
        this.getModuleManager();                         // Gets the module manager as a generic Manager<Module>
//        <ExampleModManager>this.getModuleManager();      // Gets the Module Manager, casted to our implementation
        this.loadPlugins("path/to/plugins");             // Load plugins
        this.getModuleManager().load();                  // Load mods

        this.setPrefix("[Client]");                      // Sets the message prefix
        this.setCommandManager(new CommandManager());    // Create Command Manager
        this.getCommandManager().load();                 // Load Commands
        EventManager.subscribe(new CommandHandler(this));// Handles all command related stuff

        // Simple Protocol Hack (Used for connecting to b0at.xyz for testing)
        ProtocolPatcher patcher = new ProtocolPatcher();
        patcher.setProtocol(315);
    }

    public String getName() {
        return this.info.getName();
    }

    public double getVersion() {
        return this.info.getBuild();
    }

    public static ExampleClient getInstance() {
        return instance;
    }
}
