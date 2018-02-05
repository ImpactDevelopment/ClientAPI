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

    /**
     * Creates an {@code EntityFilter} that restricts the specified entity
     *
     * @param targetEntity The entity to filter out
     * @return An entity filter that can restrict entities that can't be seen
     */
    public static EntityCheck restrictEntity(Supplier<Entity> targetEntity) {
        return new EntityCheckImpl(CheckType.RESTRICT, entity ->
                entity != targetEntity.get()
        );
    }

    /**
     * Creates an {@code EntityFilter} that restricts entities that are dead.
     *
     * @param allowDead Whether or not to allow entities that are dead
     * @return An entity filter that can restrict entities that are dead
     */
    public static EntityCheck allowDead(Supplier<Boolean> allowDead) {
        return new EntityCheckImpl(CheckType.RESTRICT, entity ->
                allowDead.get() || entity.isEntityAlive()
        );
    }

    /**
     * Creates an {@code EntityFilter} that restricts entities that can't
     * be seen by the local player.
     *
     * @param allowCantBeSeen Whether or not to allow entities that can't be seen
     * @return An entity filter that can restrict entities that can't be seen
     */
    public static EntityCheck allowCantBeSeen(Supplier<Boolean> allowCantBeSeen) {
        return new EntityCheckImpl(CheckType.RESTRICT, entity ->
                allowCantBeSeen.get() || mc.player.canEntityBeSeen(entity)
        );
    }

    /**
     * Creates an {@code EntityFilter} that restricts sleeping players.
     *
     * @param allowSleeping Whether or not to allow sleeping players
     * @return An entity filter that can restrict sleeping players
     */
    public static EntityCheck allowSleeping(Supplier<Boolean> allowSleeping) {
        return new EntityCheckImpl(CheckType.RESTRICT, entity ->
                allowSleeping.get() || !isPlayer(entity) || !((EntityPlayer) entity).isPlayerSleeping()
        );
    }

    /**
     * Creates an {@code EntityFilter} that restricts invisible entities.
     *
     * @param allowInvisible Whether or not to allow invisible entities
     * @return An entity filter that can restrict invisible entities
     */
    public static EntityCheck allowInvisible(Supplier<Boolean> allowInvisible) {
        return new EntityCheckImpl(CheckType.RESTRICT, entity ->
                allowInvisible.get() || !entity.isInvisible()
        );
    }

    /**
     * Creates an {@code EntityFilter} that restricts other
     * players that are on the same team as the local player.
     *
     * @see EntityFilters#onSameTeam(Entity, Entity)
     *
     * @param allowTeammates Whether or not to allow teammates
     * @return An entity filter that can restrict teammates
     */
    public static EntityCheck allowTeammates(Supplier<Boolean> allowTeammates) {
        return new EntityCheckImpl(CheckType.RESTRICT, entity ->
                allowTeammates.get() || !onSameTeam(entity, mc.player)
        );
    }

    /**
     * Creates an {@code EntityFilter} that restricts player,
     * hostile, and passive entities if their corresponding
     * suppliers return false.
     *
     * @see EntityFilters#isPlayer(Entity)
     * @see EntityFilters#isHostile(Entity)
     * @see EntityFilters#isPassive(Entity)
     *
     * @param allowPlayers Whether or not to allow players
     * @param allowHostiles Whether or not to allow hostiles
     * @param allowPassives Whether or not to allow passives
     * @return An entity filter that can restrict player, hostile, and passive entities
     */
    public static EntityCheck allowType(Supplier<Boolean> allowPlayers, Supplier<Boolean> allowHostiles, Supplier<Boolean> allowPassives) {
        return new EntityFilter(allowPlayers(allowPlayers), allowHostiles(allowHostiles), allowPassives(allowPassives));
    }

    /**
     * Creates an {@code EntityFilter} that restricts player
     * entities if the specified supplier returns false.
     *
     * @see EntityFilters#isPlayer(Entity)
     *
     * @param allowPlayers Whether or not to allow players
     * @return An entity filter that can restrict players
     */
    public static EntityCheck allowPlayers(Supplier<Boolean> allowPlayers) {
        return new EntityCheckImpl(CheckType.ALLOW, entity ->
                allowPlayers.get() && isPlayer(entity)
        );
    }

    /**
     * Creates an {@code EntityFilter} that restricts hostile
     * entities if the specified supplier returns false.
     *
     * @see EntityFilters#isHostile(Entity)
     *
     * @param allowHostiles Whether or not to allow hostiles
     * @return An entity filter that can restrict hostiles
     */
    public static EntityCheck allowHostiles(Supplier<Boolean> allowHostiles) {
        return new EntityCheckImpl(CheckType.ALLOW, entity ->
                allowHostiles.get() && isHostile(entity)
        );
    }

    /**
     * Creates an {@code EntityFilter} that restricts passive
     * entities if the specified supplier returns false.
     *
     * @see EntityFilters#isPassive(Entity)
     *
     * @param allowPassives Whether or not to allow passives
     * @return An entity filter that can restrict passives
     */
    public static EntityCheck allowPassives(Supplier<Boolean> allowPassives) {
        return new EntityCheckImpl(CheckType.ALLOW, entity ->
                allowPassives.get() && isPassive(entity)
        );
    }

    /**
     * Checks if two entities are on the same team
     *
     * @param e1 First entity
     * @param e2 Second entity
     * @return Whether or not the entities are on the same team
     */
    public static boolean onSameTeam(Entity e1, Entity e2) {
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
