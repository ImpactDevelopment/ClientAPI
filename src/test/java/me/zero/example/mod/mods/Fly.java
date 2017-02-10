package me.zero.example.mod.mods;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.defaults.UpdateEvent;
import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import me.zero.example.mod.category.IMovement;

/**
 * Created by Brady on 2/8/2017.
 */
@Mod(name = "Fly", description = "Allows you to fly")
public class Fly extends Module implements IMovement {

    public Fly() {
        this.toggle();
    }

    @EventHandler
    public void onUpdate(UpdateEvent event) {
        mc.player.motionY = new Boolean(mc.gameSettings.keyBindJump.isKeyDown()).compareTo(mc.gameSettings.keyBindSneak.isKeyDown()) * 0.2;
    }
}
