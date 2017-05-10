package me.zero.client.api.util;

import me.zero.client.api.manage.Node;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.value.type.BooleanType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Used to easily filter entities
 *
 * @since 1.0
 *
 * Created by Brady on 3/1/2017.
 */
public final class EntityFilter implements Helper {

    /**
     * Geneerated predicate
     */
    private final Predicate<Entity> predicate;

    /**
     * The various Boolean Types associated with filter checks
     */
    private final BooleanType walls, team, invis, sleep, player, hostile, passive;

    @SafeVarargs
    public EntityFilter(Node node, String player, String hostile, String passive, Predicate<Entity>... checks) {
        this(node, null, null, null, null, player, hostile, passive, checks);
    }

    @SafeVarargs
    public EntityFilter(Node node, String invis, String sleep, String player, String hostile, String passive, Predicate<Entity>... checks) {
        this(node, null, null, invis, sleep, player, hostile, passive, checks);
    }

    @SafeVarargs
    public EntityFilter(Node node, String walls, String team, String invis, String sleep, String player, String hostile, String passive, Predicate<Entity>... checks) {
        this.walls = (BooleanType) node.getValue(walls);
        this.team = (BooleanType) node.getValue(team);
        this.invis = (BooleanType) node.getValue(invis);
        this.sleep = (BooleanType) node.getValue(sleep);
        this.player = (BooleanType) node.getValue(player);
        this.hostile = (BooleanType) node.getValue(hostile);
        this.passive = (BooleanType) node.getValue(passive);

        this.predicate = e -> {
            for (Predicate<Entity> check : checks)
                if (!check.test(e))
                    return false;

            if (this.walls != null && !this.walls.getState() && !mc.player.canEntityBeSeen(e))
                return false;

            if (this.sleep != null && !this.sleep.getState() && (e instanceof EntityPlayer && ((EntityPlayer) e).isPlayerSleeping()))
                return false;

            if (this.invis != null && !this.invis.getState() && e.isInvisible())
                return false;

            if (this.team != null && !this.team.getState() && onSameTeam(mc.player, e))
                return false;

            if (isPlayer(e)) {
                if (this.player.getValue())
                    return true;
            } else if (isHostile(e)) {
                if (this.hostile.getValue())
                    return true;
            } else if (isPassive(e)) {
                if (this.passive.getValue())
                    return true;
            }

            return false;
        };
    }

    /**
     * Tests an entity to see if it passes this
     * filter's checks
     *
     * @since 1.0
     *
     * @param entity The entity
     * @return Whether or not it passes the checks
     */
    public final boolean test(Entity entity) {
        return this.predicate.test(entity);
    }

    /**
     * Checks a list of entities to see if it passes
     * this filter's checks, returns a filtered list
     *
     * @since 1.0
     *
     * @param entities List of entities
     * @return The filtered list
     */
    public final List<Entity> sort(List<Entity> entities) {
        return entities.stream().filter(this::test).collect(Collectors.toList());
    }

    /**
     * Checks if the specified entity is a player
     *
     * @since 1.0
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
     * @since 1.0
     *
     * @param e The entity
     * @return Whether or not the entity is a hostile
     */
    public static boolean isHostile(Entity e) {
        boolean c1 = e instanceof EntityMob;
        boolean c2 = e instanceof EntitySlime;
        boolean c3 = e instanceof EntityShulker;
        boolean c4 = e instanceof EntityGhast;
        boolean c5 = e instanceof EntityDragon;
        return c1 || c2 || c3 || c4 || c5;
    }

    /**
     * Checks if the specified entity is a passive
     *
     * @since 1.0
     *
     * @param e The entity
     * @return Whether or not the entity is a passive
     */
    public static boolean isPassive(Entity e) {
        boolean c1 = e instanceof EntityAnimal;
        boolean c2 = e instanceof EntitySquid;
        boolean c3 = e instanceof EntityBat;
        boolean c4 = e instanceof EntityVillager;
        boolean c5 = e instanceof EntitySnowman;
        boolean c6 = e instanceof EntityIronGolem;
        return c1 || c2 || c3 || c4 || c5 || c6;
    }

    /**
     * Checks if two entities are on the same team
     *
     * @since 1.0
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
}
