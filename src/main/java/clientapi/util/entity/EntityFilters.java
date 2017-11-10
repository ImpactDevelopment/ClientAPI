package clientapi.util.entity;

import clientapi.util.interfaces.Helper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.village.Village;

import java.util.function.Supplier;

/**
 * @author Brady
 * @since 11/9/2017 9:02 PM
 */
public final class EntityFilters implements Helper {

    private EntityFilters() {}

    public static EntityFilter allowCantBeSeen(Supplier<Boolean> allowCantBeSeen) {
        return entity -> allowCantBeSeen.get() || mc.player.canEntityBeSeen(entity);
    }

    public static EntityFilter allowSleeping(Supplier<Boolean> allowSleeping) {
        return entity -> allowSleeping.get() || (!isPlayer(entity) || !((EntityPlayer) entity).isPlayerSleeping());
    }

    public static EntityFilter allowInvisible(Supplier<Boolean> allowInvisible) {
        return entity -> allowInvisible.get() || !entity.isInvisible();
    }

    public static EntityFilter allowTeammates(Supplier<Boolean> allowTeammates) {
        return entity -> allowTeammates.get() || !onSameTeam(entity, mc.player);
    }

    public static EntityFilter allowPlayers(Supplier<Boolean> allowPlayers) {
        return entity -> allowPlayers.get() || !isPlayer(entity);
    }

    public static EntityFilter allowHostiles(Supplier<Boolean> allowHostiles) {
        return entity -> allowHostiles.get() || !isHostile(entity);
    }

    public static EntityFilter allowPassives(Supplier<Boolean> allowPassives) {
        return entity -> allowPassives.get() || !isPassive(entity);
    }

    /**
     * Checks if two entities are on the same team
     *
     * @param e1 First entity
     * @param e2 Second entity
     * @return Whether or not the entities are on the same team
     */
    public static boolean onSameTeam(Entity e1, Entity e2) {
        if (!(e1 instanceof EntityPlayer) || !(e2 instanceof EntityPlayer))
            return false;

        return e1.isOnSameTeam(e2);
    }

    /**
     * Checks if the specified entity is a player
     *
     * @param e The entity
     * @return Whether or not the entity is a player
     */
    public static boolean isPlayer(Entity e) {
        return e instanceof EntityPlayer;
    }

    /**
     * Checks if the specified entity is a hostile
     *
     * @param e The entity
     * @return Whether or not the entity is a hostile
     */
    public static boolean isHostile(Entity e) {
        if (e instanceof EntityPigZombie)
            return isAngry((EntityPigZombie) e);

        if (e instanceof EntityIronGolem)
            return isAngry((EntityIronGolem) e);

        if (e instanceof EntityPolarBear)
            return isAngry((EntityPolarBear) e);

        if (e instanceof EntityMob)
            return true;

        if (e instanceof EntitySlime)
            return true;

        if (e instanceof EntityShulker)
            return true;

        if (e instanceof EntityGhast)
            return true;

        if (e instanceof EntityDragon)
            return true;

        return false;
    }

    /**
     * Checks if the specified entity is a passive
     *
     * @param e The entity
     * @return Whether or not the entity is a passive
     */
    public static boolean isPassive(Entity e) {
        if (e instanceof EntityPigZombie)
            return !isAngry((EntityPigZombie) e);

        if (e instanceof EntityIronGolem)
            return !isAngry((EntityIronGolem) e);

        if (e instanceof EntityPolarBear)
            return !isAngry((EntityPolarBear) e);

        if (e instanceof EntityAnimal)
            return true;

        if (e instanceof EntitySquid)
            return true;

        if (e instanceof EntityBat)
            return true;

        if (e instanceof EntityVillager)
            return true;

        if (e instanceof EntitySnowman)
            return true;

        return false;
    }

    private static boolean isAngry(EntityPigZombie e) {
        // Zombie Pigmen are nice and easy to check
        return e.isAngry();
    }

    private static boolean isAngry(EntityIronGolem e) {
        // Manually spawned golems will not turn hostile
        if (e.isPlayerCreated())
            return false;

        // Check if the village dislike the player
        {
            Village village = e.getVillage();
            // noinspection ConstantConditions
            if (village != null && village.isPlayerReputationTooLow(mc.player.getName()))
                return true;
        }

        // Check if the player is being targeted
        if (isTargeting(e, mc.player))
            return true;

        return false;
    }

    private static boolean isAngry(EntityPolarBear e) {
        // Polar Bear Cubs can't be angry
        if (e.isChild())
            return false;

        // Check if the bear is targeting the player
        if (isTargeting(e, mc.player))
            return true;

        return false;
    }

    private static boolean isTargeting(EntityLiving entity, EntityLivingBase target) {
        EntityLivingBase attackTarget = entity.getAttackTarget();
        EntityLivingBase revengeTarget = entity.getRevengeTarget();

        if (attackTarget != null && attackTarget.equals(target))
            return true;

        if (revengeTarget != null && revengeTarget.equals(target))
            return true;

        return false;
    }
}
