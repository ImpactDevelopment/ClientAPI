package me.zero.example.mod.mods;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.defaults.Render2DEvent;
import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import me.zero.example.mod.category.IRender;

/**
 * Created by Brady on 2/8/2017.
 */
@Mod(name = "HUD", description = "Displays an In-Game HUD")
public class Hud extends Module implements IRender {

    public Hud() {
        this.toggle();
    }

    @EventHandler
    public void onRender2D(Render2DEvent event) {
        mc.fontRendererObj.drawString("ExampleClient", 2, 2, -1);
    }
}
