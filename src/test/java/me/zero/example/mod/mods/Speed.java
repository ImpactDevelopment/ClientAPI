package me.zero.example.mod.mods;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.defaults.MoveEvent;
import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import me.zero.client.api.value.annotation.NumberValue;
import me.zero.example.mod.category.IMovement;
import org.lwjgl.input.Keyboard;

/**
 * Created by Brady on 2/11/2017.
 */
@Mod(name = "Speed", description = "A basic speed module", bind = Keyboard.KEY_Z)
public class Speed extends Module implements IMovement {

    @NumberValue(name = "Speed", minimum = 1, maximum = 10)
    private double speed = 3;

    @EventHandler
    public void onMove(MoveEvent event) {
        event.setX(event.getX() * speed).setZ(event.getZ() * speed);
    }
}
