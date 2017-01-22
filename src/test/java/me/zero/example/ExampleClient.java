package me.zero.example;

import me.zero.client.api.Client;
import me.zero.client.api.module.ModuleType;

/**
 * Created by Brady on 1/19/2017.
 */
public class ExampleClient extends Client {

    @Override
    public void preInit() {
        // Transformers and Module Types
        ModuleType.create("Combat", "Movement", "Player", "Exploit");
    }

    @Override
    public void onInit() {
        // Modules
    }

    @Override
    public void postInit() {
        // UI
    }
}
