package me.zero.example;

import me.zero.client.api.Client;
import me.zero.client.api.ClientInfo;
import me.zero.client.api.util.factory.AuthenticationFactory;
import me.zero.client.load.ClientAPI;
import me.zero.client.load.ClientLoader;
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
    public void onInit(ClientLoader loader) {
        System.out.println("ExampleClient: onInit()");

        // Login to our account
        AuthenticationFactory.create().username("example@host.xyz").password("12345").login();

        // Gets the client info for later usage
        this.info = loader.getDiscoveredInfo();

        // Create Module Manager
        this.setModuleManager(new ExampleModManager());

        // Gets the module manager as a generic Manager<Module>
        this.getModuleManager();

        // Gets the Module Manager, casted to our implementation
        this.getModuleManager(ExampleModManager.class);

        // Load plugins
        this.loadPlugins("path/to/plugins");

        // Load mods
        this.getModuleManager().load();
    }

    @Override
    public void postInit() {
        System.out.println("ExampleClient: postInit()");
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
