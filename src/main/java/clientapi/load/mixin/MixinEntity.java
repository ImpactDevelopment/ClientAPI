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

package clientapi.load.mixin;

import clientapi.util.math.Vec3;
import clientapi.ClientAPI;
import clientapi.event.defaults.game.entity.EntityCollisionEvent;
import clientapi.util.math.Vec2;
import clientapi.load.mixin.wrapper.IEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.util.math.AxisAlignedBB;
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
public abstract class MixinEntity implements IEntity {

    @Shadow public double posX;
    @Shadow public double posY;
    @Shadow public double posZ;
    @Shadow public double motionX;
    @Shadow public double motionY;
    @Shadow public double motionZ;
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
    @Shadow public boolean onGround;

    @Shadow public void move(MoverType type, double x, double y, double z) {}
    @Shadow public abstract boolean isSprinting();
    @Shadow public abstract boolean isRiding();
    @Shadow public abstract AxisAlignedBB getEntityBoundingBox();

    private Vec3 pos, prevPos, lastTickPos;
    private Vec2 rotation, prevRotation;

    @Inject(method = "applyEntityCollision", at = @At("HEAD"), cancellable = true)
    private void applyEntityCollision(Entity entityIn, CallbackInfo ci) {
        EntityCollisionEvent event = new EntityCollisionEvent((Entity) (Object) this, entityIn);
        ClientAPI.EVENT_BUS.post(event);
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

        return pos.x(posX).y(posY).z(posZ);
    }

    @Override
    public Vec3 getPrevPos() {
        if (prevPos == null)
            prevPos = new Vec3();

        return prevPos.x(prevPosX).y(prevPosY).z(prevPosZ);
    }

    @Override
    public Vec3 getLastTickPos() {
        if (lastTickPos == null)
            lastTickPos = new Vec3();

        return lastTickPos.x(lastTickPosX).y(lastTickPosY).z(lastTickPosZ);
    }

    @Override
    public Vec2 getRotations() {
        if (rotation == null)
            rotation = new Vec2();

        return rotation.x(rotationYaw).y(rotationPitch);
    }

    @Override
    public Vec2 getPrevRotations() {
        if (prevRotation == null)
            prevRotation = new Vec2();

        return prevRotation.x(prevRotationYaw).y(prevRotationPitch);
    }

    @Override
    public Vec3 interpolate(float ticks) {
        return this.getLastTickPos().add(this.getPos().sub(this.getLastTickPos()).scale(ticks));
    }
}
