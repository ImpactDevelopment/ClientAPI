package me.zero.example;

import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;

/**
 * Created by Brady on 1/27/2017.
 */
@Mod(name = "Example", description = "An Example Module", type = "Misc")
public class ExampleModule extends Module {

    @Override
    public void onEnable() {
        System.out.println("Enabled Example Mod");
    }

    @Override
    public void onDisable() {
        System.out.println("Disabled Example Mod");
    }
}
