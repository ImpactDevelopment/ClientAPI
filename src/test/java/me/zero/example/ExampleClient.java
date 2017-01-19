package me.zero.example;

import me.zero.client.api.Client;
import me.zero.client.api.module.ModuleType;

/**
 * Created by Brady on 1/19/2017.
 */
public class ExampleClient extends Client {

    @Override
    public void preInit() {
        ModuleType.create("Combat", "Render", "Movement", "Player", "World");
    }

    @Override
    public void onInit() {

    }

    @Override
    public void postInit() {

    }
}
