package me.zero.example;

import me.zero.client.api.Client;
import me.zero.client.api.ClientInfo;
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
    public void preInit() {
        System.out.println("ExampleClient: preInit()");

        // Retrieve the ClientLoader
        ClientLoader loader = ClientAPI.getAPI().getLoader();

        // Retrieve ClientInfo for later usage
        this.info = loader.getDiscoveredInfo();

        // Register Transformer
        loader.registerTransformer(new ExampleTransformer());

        // Create Module Manager
        this.setModuleManager(new ExampleModManager());

        // Gets the Module Manager, casted to our implementation
        this.getModuleManager(ExampleModManager.class);

        // Gets the Module Manager, as a Manager<Module>
        this.getModuleManager();
    }

    @Override
    public void onInit() {
        System.out.println("ExampleClient: onInit()");

        this.getModuleManager().load();
        this.loadPlugins("path/to/plugins");
    }

    @Override
    public void postInit() {
        System.out.println("ExampleClient: postInit()");
        // UI
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
