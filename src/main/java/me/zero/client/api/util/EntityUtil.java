package me.zero.client.api.util;

import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

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
            if (!mc.world.loadedEntityList.contains(entity))
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
    public void setAll(Vec3d pos, Vec2f angles) {
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
    public void setAll(Vec3d pos, Vec3d prev, Vec3d lastTick, Vec2f angles, Vec2f prevAngles) {
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
    public void setPos(Vec3d pos) {
        entity.posX = pos.xCoord;
        entity.posY = pos.yCoord;
        entity.posZ = pos.zCoord;
    }

    /**
     * Sets the previous entity position
     *
     * @since 1.0
     *
     * @param pos New position
     */
    public void setPrevPos(Vec3d pos) {
        entity.prevPosX = pos.xCoord;
        entity.prevPosY = pos.yCoord;
        entity.prevPosZ = pos.zCoord;
    }

    /**
     * Sets the last tick entity position
     *
     * @since 1.0
     *
     * @param pos New position
     */
    public void setLastTickPos(Vec3d pos) {
        entity.lastTickPosX = pos.xCoord;
        entity.lastTickPosY = pos.yCoord;
        entity.lastTickPosZ = pos.zCoord;
    }

    /**
     * Sets the entity rotations
     *
     * @since 1.0
     *
     * @param rotations New position
     */
    public void setRotations(Vec2f rotations) {
        entity.rotationYaw = rotations.x;
        entity.rotationPitch = rotations.y;
    }

    /**
     * Sets the previous entity rotations
     *
     * @since 1.0
     *
     * @param rotations New position
     */
    public void setPrevRotations(Vec2f rotations) {
        entity.prevRotationYaw = rotations.x;
        entity.prevRotationPitch = rotations.y;
    }

    /**
     * Converts the entity position to a {@code Vec3d}
     *
     * @since 1.0
     *
     * @return Position as a {@code Vec3d}
     */
    public Vec3d getPos() {
        return new Vec3d(entity.posX, entity.posY, entity.posZ);
    }

    /**
     * Converts the previous entity position to a {@code Vec3d}
     *
     * @since 1.0
     *
     * @return Previous position as a {@code Vec3d}
     */
    public Vec3d getPrevPos() {
        return new Vec3d(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
    }

    /**
     * Converts the last tick entity position to a {@code Vec3d}
     *
     * @since 1.0
     *
     * @return Last tick position as a {@code Vec3d}
     */
    public Vec3d getLastTickPos() {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ);
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
    public Vec3d predictPos(int ticks) {
        return getPos().add(getPos().subtract(getLastTickPos()).scale(ticks));
    }

    /**
     * Converts the entity rotation angles to a {@code Vec2f}
     *
     * @since 1.0
     *
     * @return Rotations as a {@code Vec2f}
     */
    public Vec2f getRotations() {
        return new Vec2f(entity.rotationYaw, entity.rotationPitch);
    }

    /**
     * Converts the previous entity rotation angles to a {@code Vec2f}
     *
     * @since 1.0
     *
     * @return Previous rotations as a {@code Vec2f}
     */
    public Vec2f getPrevRotations() {
        return new Vec2f(entity.prevRotationYaw, entity.prevRotationPitch);
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
