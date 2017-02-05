package me.zero.client.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

/**
 * An
 *
 * @since 1.0
 *
 * Created by Brady on 2/4/2017.
 */
public class EntityUtil {

    private Entity entity;

    public EntityUtil(Entity entity) {
        this.entity = entity;
    }

    public void setAll(Vec3d pos, Vec2f angles) {
        this.setAll(pos, pos, pos, angles, angles);
    }

    public void setAll(Vec3d pos, Vec3d prev, Vec3d lastTick, Vec2f angles, Vec2f prevAngles) {
        this.setPos(pos);
        this.setPrevPos(prev);
        this.setLastTickPos(lastTick);
        this.setRotations(angles);
        this.setPrevRotations(prevAngles);
    }

    public void setPos(Vec3d pos) {
        entity.posX = pos.xCoord;
        entity.posY = pos.yCoord;
        entity.posZ = pos.zCoord;
    }

    public void setPrevPos(Vec3d pos) {
        entity.prevPosX = pos.xCoord;
        entity.prevPosY = pos.yCoord;
        entity.prevPosZ = pos.zCoord;
    }

    public void setLastTickPos(Vec3d pos) {
        entity.lastTickPosX = pos.xCoord;
        entity.lastTickPosY = pos.yCoord;
        entity.lastTickPosZ = pos.zCoord;
    }

    public void setRotations(Vec2f rotations) {
        entity.rotationYaw = rotations.x;
        entity.rotationPitch = rotations.y;
    }

    public void setPrevRotations(Vec2f rotations) {
        entity.prevRotationYaw = rotations.x;
        entity.prevRotationPitch = rotations.y;
    }

    public Vec3d getPos() {
        return new Vec3d(entity.posX, entity.posY, entity.posZ);
    }

    public Vec3d getPrevPos() {
        return new Vec3d(entity.prevPosX, entity.prevPosY, entity.prevPosZ);
    }

    public Vec3d getLastTickPos() {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ);
    }

    public Vec2f getRotations() {
        return new Vec2f(entity.rotationYaw, entity.rotationPitch);
    }

    public Vec2f getPrevRotations() {
        return new Vec2f(entity.prevRotationYaw, entity.prevRotationPitch);
    }

    public Entity getEntity() {
        return this.entity;
    }
}
