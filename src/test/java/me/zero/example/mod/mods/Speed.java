package me.zero.example.mod.mods;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.defaults.MoveEvent;
import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import me.zero.client.api.util.interfaces.annotation.Label;
import me.zero.client.api.value.annotation.NumberValue;
import me.zero.example.mod.category.IMovement;
import org.lwjgl.input.Keyboard;

/**
 * Created by Brady on 2/11/2017.
 */
@Mod(name = "Speed", description = "A basic speed module", bind = Keyboard.KEY_Z)
public class Speed extends Module implements IMovement {

    @Label(name = "Speed", description = "The multiplier for your speed")
    @NumberValue(min = 1, max = 10)
    private double speed = 3;

    @EventHandler
    private Listener<MoveEvent> moveListener = new Listener<>(event -> {
        event.setX(event.getX() * speed).setZ(event.getZ() * speed);
    });
}
