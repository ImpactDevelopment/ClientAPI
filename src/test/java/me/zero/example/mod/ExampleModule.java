package me.zero.example.mod;

import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import me.zero.example.mod.category.IMisc;

/**
 * Created by Brady on 1/27/2017.
 */
@Mod(name = "Example", description = "An Example Module")
public class ExampleModule extends Module implements IMisc {

    @Override
    public void onEnable() {
        System.out.println("Enabled Example Mod");
    }

    @Override
    public void onDisable() {
        System.out.println("Disabled Example Mod");
    }
}
