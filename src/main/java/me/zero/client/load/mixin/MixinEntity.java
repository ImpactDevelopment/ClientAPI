package me.zero.client.load.mixin;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.EntityCollisionEvent;
import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;
import me.zero.client.wrapper.IEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Brady on 4/27/2017.
 */
@Mixin(Entity.class)
public class MixinEntity implements IEntity {

    @Shadow public double posX;
    @Shadow public double posY;
    @Shadow public double posZ;
    @Shadow public double prevPosX;
    @Shadow public double prevPosY;
    @Shadow public double prevPosZ;
    @Shadow public double lastTickPosX;
    @Shadow public double lastTickPosY;
    @Shadow public double lastTickPosZ;
    @Shadow public float rotationYaw;
    @Shadow public float rotationPitch;
    @Shadow public float prevRotationYaw;
    @Shadow public float prevRotationPitch;

    @Shadow public void move(MoverType type, double x, double y, double z) {}

    @Inject(method = "applyEntityCollision", at = @At("HEAD"), cancellable = true)
    public void applyEntityCollision(Entity entityIn, CallbackInfo ci) {
        EntityCollisionEvent event = new EntityCollisionEvent((Entity) (Object) this, entityIn);
        EventManager.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Override
    public void setPos(Vec3 pos) {
        this.posX = pos.getX();
        this.posY = pos.getY();
        this.posZ = pos.getZ();
    }

    @Override
    public void setPrevPos(Vec3 pos) {
        this.prevPosX = pos.getX();
        this.prevPosY = pos.getY();
        this.prevPosZ = pos.getZ();
    }

    @Override
    public void setLastTickPos(Vec3 pos) {
        this.lastTickPosX = pos.getX();
        this.lastTickPosY = pos.getY();
        this.lastTickPosZ = pos.getZ();
    }

    @Override
    public void setRotations(Vec2 rotations) {
        this.rotationYaw = rotations.getX();
        this.rotationPitch = rotations.getY();
    }

    @Override
    public void setPrevRotations(Vec2 rotations) {
        this.prevRotationYaw = rotations.getX();
        this.prevRotationPitch = rotations.getY();
    }

    @Override
    public Vec3 getPos() {
        return new Vec3(posX, posY, posZ);
    }

    @Override
    public Vec3 getPrevPos() {
        return new Vec3(prevPosX, prevPosY, prevPosZ);
    }

    @Override
    public Vec3 getLastTickPos() {
        return new Vec3(lastTickPosX, lastTickPosY, lastTickPosZ);
    }

    @Override
    public Vec3 interpolate(float ticks) {
        return this.getLastTickPos().add(this.getPos().sub(this.getLastTickPos()).scale(ticks));
    }

    @Override
    public Vec2 getRotations() {
        return new Vec2(rotationYaw, rotationPitch);
    }

    @Override
    public Vec2 getPrevRotations() {
        return new Vec2(prevRotationYaw, prevRotationPitch);
    }
}
