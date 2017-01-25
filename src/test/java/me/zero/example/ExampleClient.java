package me.zero.example;

import me.zero.client.api.Client;
import me.zero.client.api.ClientInfo;
import me.zero.client.api.module.ModuleType;
import me.zero.client.load.ClientAPI;
import me.zero.client.load.ClientLoader;

/**
 * Created by Brady on 1/19/2017.
 */
public class ExampleClient extends Client {

    private ClientInfo info;

    @Override
    public void preInit() {
        // Retrieve the ClientLoader
        ClientLoader loader = ClientAPI.getAPI().getLoader();

        // Retrieve ClientInfo for later usage
        this.info = loader.getDiscoveredInfo();

        // Register Module Types
        ModuleType.create("Combat", "Movement", "Player", "Exploit");

        // Register Transformer
        loader.registerTransformer(new ExampleTransformer());
    }

    @Override
    public void onInit() {
        // Modules
    }

    @Override
    public void postInit() {
        // UI
    }

    public String getName() {
        return this.info.getName();
    }

    public double getVersion() {
        return this.info.getBuild();
    }
}
