package me.zero.example.mod.mods;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.defaults.LivingUpdateEvent;
import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Keyboard;

/**
 * Created by Brady on 2/10/2017.
 */
@Mod(name = "NoSlow", description = "While using items, You aren't slowed down", bind = Keyboard.KEY_N)
public class NoSlow extends Module {

    private EnumHand lastActive;

    @EventHandler
    private Listener<LivingUpdateEvent> livingUpdateListener = new Listener<>(event -> {
        if (mc.player.isHandActive()) {
            switch (event.getState()) {
                case PRE: {
                    lastActive = mc.player.getActiveHand();
                    mc.player.resetActiveHand();
                    break;
                }
                case POST: {
                    mc.player.setActiveHand(lastActive);
                    break;
                }
            }
        }
    });
}
