package me.zero.client.api.util;

import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A way to easily interact with entities without
 * extra, unnecessary code.
 *
 * @since 1.0
 *
 * Created by Brady on 2/4/2017.
 */
public class EntityUtil implements Helper {

    /**
     * Map of {@code EntityUtils} to make sure we're not
     * creating tons of them every single render tick.
     */
    private static Map<Entity, EntityUtil> utils = new ConcurrentHashMap<>();

    /**
     * Entity represented by this {@code EntityUtil}
     */
    private Entity entity;

    private EntityUtil(Entity entity) {
        this.entity = entity;
    }

    /**
     * Returns the {@code EntityUtil} associated with the
     * specified entity. This is better than creating a new
     * EntityUtil because it stores the EntityUtils associated
     * with entities inside of a List, so if the util already
     * exists, then we don't need to create a new one.
     *
     * @since 1.0
     *
     * @param entity The entity
     * @return The entityutil
     */
    public static EntityUtil get(Entity entity) {
        EntityUtil util = utils.get(entity);
        if (util != null)
            return util;
        utils.put(entity, new EntityUtil(entity));
        cleanUp();
        return get(entity);
    }

    /**
     * Cleans up any entities that no longer exist, and
     * removes the {@code EntityUtil} associated with them.
     *
     * @since 1.0
     */
    private static void cleanUp() {
        utils.keySet().forEach(entity -> {
            if (!mc.world.loadedEntityList.contains(entity) || entity.isDead)
                utils.remove(entity);
        });
    }

    /**
     * Sets all forms of position and rotation in
     * this entity to the specified position and rotation
     *
     * @since 1.0
     *
     * @param pos The position
     * @param angles The rotation
     */
    public void setAll(Vec3 pos, Vec2 angles) {
        this.setAll(pos, pos, pos, angles, angles);
    }

    /**
     * Sets all forms of position of rotation to
     * their specified vectors
     *
     * @since 1.0
     *
     * @param pos The position
     * @param prev The previous position
     * @param lastTick The last tick position
     * @param angles The angles
     * @param prevAngles The previous angles
     */
    public void setAll(Vec3 pos, Vec3 prev, Vec3 lastTick, Vec2 angles, Vec2 prevAngles) {
        this.setPos(pos);
        this.setPrevPos(prev);
        this.setLastTickPos(lastTick);
        this.setRotations(angles);
        this.setPrevRotations(prevAngles);
    }

    /**
     * Sets the entity position
     *
     * @since 1.0
     *
     * @param pos New position
     */
    public void setPos(Vec3 pos) {
        entity.posX = pos.getX();
        entity.posY = pos.getY();
        entity.posZ = pos.getZ();
    }

    /**
     * Sets the previous entity position
     *
     * @since 1.0
     *
     * @param pos New position
     */
    public void setPrevPos(Vec3 pos) {
        entity.prevPosX = pos.getX();
        entity.prevPosY = pos.getY();
        entity.prevPosZ = pos.getZ();
    }

    /**
     * Sets the last tick entity position
     *
     * @since 1.0
     *
     * @param pos New position
     */
    public void setLastTickPos(Vec3 pos) {
        entity.lastTickPosX = pos.getX();
        entity.lastTickPosY = pos.getY();
        entity.lastTickPosZ = pos.getZ();
    }

    /**
     * Sets the entity rotations
     *
     * @since 1.0
     *
     * @param rotations New position
     */
    public void setRotations(Vec2 rotations) {
        entity.rotationYaw = rotations.getX();
        entity.rotationPitch = rotations.getY();
    }

    /**
     * Sets the previous entity rotations
     *
     * @since 1.0
     *
     * @param rotations New position
     */
    public void setPrevRotations(Vec2 rotations) {
        entity.prevRotationYaw = rotations.getX();
        entity.prevRotationPitch = rotations.getY();
    }

    /**
     * Converts the entity position to a {@code Vec3d}
     *
     * @since 1.0
     *
     * @return Position as a {@code Vec3d}
     */
    public Vec3 getPos() {
        return new Vec3(entity.posX, entity.posY, entity.posZ);
    }

    /**
     * Converts the previous entity position to a {@code Vec3d}
     *
     * @since 1.0
     *
     * @return Previous position as a {@code Vec3d}
     */
    public Vec3 getPrevPos() {
        return new Vec3(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
    }

    /**
     * Converts the last tick entity position to a {@code Vec3d}
     *
     * @since 1.0
     *
     * @return Last tick position as a {@code Vec3d}
     */
    public Vec3 getLastTickPos() {
        return new Vec3(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ);
    }

    /**
     * Calculates the predicted position that an entity will
     * be in the specified amount of ticks
     *
     * @since 1.0
     *
     * @param ticks Time in ticks predicted
     * @return The predicted position
     */
    public Vec3 predictPos(int ticks) {
        return getPos().add(getPos().sub(getLastTickPos()).scale(ticks));
    }

    /**
     * Converts the entity rotation angles to a {@code Vec2f}
     *
     * @since 1.0
     *
     * @return Rotations as a {@code Vec2f}
     */
    public Vec2 getRotations() {
        return new Vec2(entity.rotationYaw, entity.rotationPitch);
    }

    /**
     * Converts the previous entity rotation angles to a {@code Vec2f}
     *
     * @since 1.0
     *
     * @return Previous rotations as a {@code Vec2f}
     */
    public Vec2 getPrevRotations() {
        return new Vec2(entity.prevRotationYaw, entity.prevRotationPitch);
    }

    /**
     * Returns the {@code Entity} that this {@code EntityUtil} represents
     *
     * @since 1.0
     *
     * @return The Entity
     */
    public Entity getEntity() {
        return this.entity;
    }
}
