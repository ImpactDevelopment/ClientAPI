/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.util;

import clientapi.manage.Node;
import clientapi.util.interfaces.Helper;
import clientapi.value.type.BooleanType;

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

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Used to easily filter entities
 *
 * @author Brady
 * @since 3/1/2017 12:00 PM
 */
public final class EntityFilter implements Helper {

    /**
     * Geneerated predicate
     */
    private final Predicate<Entity> predicate;

    /**
     * The various Boolean Types associated with filter checks
     */
    private final BooleanType walls, team, invis, sleep, player, hostile,
        passive;

    @SafeVarargs
    public EntityFilter(Node node, String player, String hostile,
        String passive, Predicate<Entity>... checks) {
        this(node, null, null, null, null, player, hostile, passive, checks);
    }

    @SafeVarargs
    public EntityFilter(Node node, String invis, String sleep, String player,
        String hostile, String passive, Predicate<Entity>... checks) {
        this(node, null, null, invis, sleep, player, hostile, passive, checks);
    }

    @SafeVarargs
    public EntityFilter(Node node, String walls, String team, String invis,
        String sleep, String player, String hostile, String passive,
        Predicate<Entity>... checks) {
        this.walls = (BooleanType) node.getValue(walls);
        this.team = (BooleanType) node.getValue(team);
        this.invis = (BooleanType) node.getValue(invis);
        this.sleep = (BooleanType) node.getValue(sleep);
        this.player = (BooleanType) node.getValue(player);
        this.hostile = (BooleanType) node.getValue(hostile);
        this.passive = (BooleanType) node.getValue(passive);

        this.predicate = e -> {
            for (Predicate<Entity> check : checks)
                if (!check.test(e)) return false;

            if (this.walls != null && !this.walls.getState()
                && !mc.player.canEntityBeSeen(e)) return false;

            if (this.sleep != null && !this.sleep.getState()
                && (e instanceof EntityPlayer
                    && ((EntityPlayer) e).isPlayerSleeping()))
                return false;

            if (this.invis != null && !this.invis.getState() && e.isInvisible())
                return false;

            if (this.team != null && !this.team.getState()
                && onSameTeam(mc.player, e)) return false;

            if (isPlayer(e)) {
                if (this.player.getValue()) return true;
            } else if (isHostile(e)) {
                if (this.hostile.getValue()) return true;
            } else if (isPassive(e)) {
                if (this.passive.getValue()) return true;
            }

            return false;
        };
    }

    /**
     * Tests an entity to see if it passes this filter's checks
     *
     * @param entity The entity
     * @return Whether or not it passes the checks
     */
    public final boolean test(Entity entity) {
        return this.predicate.test(entity);
    }

    /**
     * Checks a list of entities to see if it passes this filter's checks,
     * returns a filtered list
     *
     * @param entities List of entities
     * @return The filtered list
     */
    public final List<Entity> sort(List<Entity> entities) {
        return entities.stream().filter(this::test)
            .collect(Collectors.toList());
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
        if (e instanceof EntityPigZombie) return isAngry((EntityPigZombie) e);

        if (e instanceof EntityIronGolem) return isAngry((EntityIronGolem) e);

        if (e instanceof EntityPolarBear) return isAngry((EntityPolarBear) e);

        if (e instanceof EntityMob) return true;
        if (e instanceof EntitySlime) return true;
        if (e instanceof EntityShulker) return true;
        if (e instanceof EntityGhast) return true;
        if (e instanceof EntityDragon) return true;

        return false;
    }

    /**
     * Checks if the specified entity is a passive
     *
     * @param e The entity
     * @return Whether or not the entity is a passive
     */
    public static boolean isPassive(Entity e) {
        if (e instanceof EntityPigZombie) return !isAngry((EntityPigZombie) e);

        if (e instanceof EntityIronGolem) return !isAngry((EntityIronGolem) e);

        if (e instanceof EntityPolarBear) return !isAngry((EntityPolarBear) e);

        if (e instanceof EntityAnimal) return true;
        if (e instanceof EntitySquid) return true;
        if (e instanceof EntityBat) return true;
        if (e instanceof EntityVillager) return true;
        if (e instanceof EntitySnowman) return true;

        return false;
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

    private static boolean isAngry(EntityPigZombie e) {
        // Zombie Pigmen are nice and easy to check
        return e.isAngry();
    }

    private static boolean isAngry(EntityIronGolem e) {
        // Manually spawned golems will not turn hostile
        if (e.isPlayerCreated()) return false;

        // Check if the village dislike the player
        {
            Village village = e.getVillage();
            if (village != null
                && village.isPlayerReputationTooLow(mc.player.getName()))
                return true;
        }

        // Check if the player is being targeted
        if (isTargeting(e, mc.player)) return true;

        return false;
    }

    private static boolean isAngry(EntityPolarBear e) {
        // Polar Bear Cubs can't be angry
        if (e.isChild()) return false;

        // Check if the bear is targeting the player
        if (isTargeting(e, mc.player)) return true;

        return false;
    }

    private static boolean isTargeting(EntityLiving entity,
        EntityLivingBase target) {
        EntityLivingBase attackTarget = entity.getAttackTarget();
        EntityLivingBase revengeTarget = entity.getRevengeTarget();

        if (attackTarget != null && attackTarget.equals(target)) return true;
        if (revengeTarget != null && revengeTarget.equals(target)) return true;

        return false;
    }
}
