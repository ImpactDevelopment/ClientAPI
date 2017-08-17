package me.zero.example.mod.mods;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import clientapi.event.defaults.game.entity.MoveEvent;
import clientapi.module.Mod;
import clientapi.module.Module;
import clientapi.util.annotation.Label;
import clientapi.value.annotation.NumberValue;
import me.zero.example.mod.category.IMovement;
import org.lwjgl.input.Keyboard;

/**
 * Created by Brady on 2/11/2017.
 */
@Mod(name = "Speed", description = "A basic speed module", bind = Keyboard.KEY_Z)
public final class Speed extends Module implements IMovement {

    @Label(name = "Speed", id = "speed", description = "The multiplier for your speed")
    @NumberValue(min = 1, max = 10)
    private double speed = 3;

    @EventHandler
    private Listener<MoveEvent> moveListener = new Listener<>(event -> {
        event.x(event.getX() * speed).z(event.getZ() * speed);
    });
}
