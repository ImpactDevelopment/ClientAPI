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
 * @author Brady
 * @since 4/27/2017 12:00 PM
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
    @Shadow public int ticksExisted;

    private Vec3 pos, prevPos, lastTickPos;
    private Vec2 rotation, prevRotation;
    private int lastTicksExisted;

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
        if (pos == null)
            pos = new Vec3();

        if (needsUpdate())
            pos.x(posX).y(posY).z(posZ);

        return pos;
    }

    @Override
    public Vec3 getPrevPos() {
        if (prevPos == null)
            prevPos = new Vec3();

        if (needsUpdate())
            prevPos.x(prevPosX).y(prevPosY).z(prevPosZ);

        return prevPos;
    }

    @Override
    public Vec3 getLastTickPos() {
        if (lastTickPos == null)
            lastTickPos = new Vec3();

        if (needsUpdate())
            lastTickPos.x(lastTickPosX).y(lastTickPosY).z(lastTickPosZ);

        return lastTickPos;
    }

    @Override
    public Vec2 getRotations() {
        if (rotation == null)
            rotation = new Vec2();

        if (needsUpdate())
            rotation.x(rotationYaw).y(rotationPitch);

        return rotation;
    }

    @Override
    public Vec2 getPrevRotations() {
        if (prevRotation == null)
            prevRotation = new Vec2();

        if (needsUpdate())
            prevRotation.x(prevRotationYaw).y(prevRotationPitch);

        return prevRotation;
    }

    @Override
    public Vec3 interpolate(float ticks) {
        return this.getLastTickPos().add(this.getPos().sub(this.getLastTickPos()).scale(ticks));
    }

    private boolean needsUpdate() {
        boolean update = ticksExisted != lastTicksExisted;
        lastTicksExisted = ticksExisted;
        return update;
    }
}
