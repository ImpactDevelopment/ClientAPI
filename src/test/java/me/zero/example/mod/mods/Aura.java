package me.zero.example.mod.mods;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.defaults.MotionUpdateEvent;
import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import me.zero.client.api.util.EntityUtil;
import me.zero.client.api.util.math.Vec2;
import me.zero.example.mod.category.ICombat;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Keyboard;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Brady on 2/12/2017.
 */
@Mod(name = "Aura", description = "XD", bind = Keyboard.KEY_K)
public class Aura extends Module implements ICombat {

    private Entity target;

    @EventHandler
    private Listener<MotionUpdateEvent> motionUpdateListener = new Listener<>(event -> {
       switch (event.getType()) {
           case PRE: {
               List<Entity> entities = mc.world.loadedEntityList.stream().filter(e ->
                       e instanceof EntityLivingBase && !e.isDead && e != mc.player && e.getDistanceToEntity(mc.player) < 4.25)
                       .sorted(Comparator.comparingDouble(e -> mc.player.getDistanceToEntity(e)))
                       .collect(Collectors.toList());
               if (entities.size() > 0) {
                   target = entities.get(0);
                   EntityUtil me = EntityUtil.get(mc.player);
                   EntityUtil them = EntityUtil.get(target);
                   Vec2 rotations = me.getPos().rotationsTo(them.getPos());
                   event.yaw(rotations.getX()).pitch(rotations.getY());
               } else {
                   target = null;
               }
               break;
           }
           case POST: {
               if (target != null && mc.player.getCooledAttackStrength(0F) == 1F) {
                   mc.playerController.attackEntity(mc.player, target);
                   mc.player.swingArm(EnumHand.MAIN_HAND);
               }
               break;
           }
       }
    });
}
