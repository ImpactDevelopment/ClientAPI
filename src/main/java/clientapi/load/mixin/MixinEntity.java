/*
 * Copyright 2018 ImpactDevelopment
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

import clientapi.ClientAPI;
import clientapi.event.defaults.game.entity.EntityCollisionEvent;
import clientapi.load.mixin.extension.IEntity;
import clientapi.util.math.Vec2;
import clientapi.util.math.Vec3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 4/27/2017
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

    @Shadow public void move(MoverType type, double x, double y, double z) {}
    @Shadow public abstract float getEyeHeight();

    private Vec3 pos, prevPos, lastTickPos, headPos, motion;
    private Vec2 rotation, prevRotation;

    @Inject(method = "applyEntityCollision", at = @At("HEAD"), cancellable = true)
    private void applyEntityCollision(Entity entityIn, CallbackInfo ci) {
        EntityCollisionEvent event = new EntityCollisionEvent((Entity) (Object) this, entityIn);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Override
    public final void setPos(Vec3 pos) {
        this.posX = pos.getX();
        this.posY = pos.getY();
        this.posZ = pos.getZ();
    }

    @Override
    public final void setPrevPos(Vec3 pos) {
        this.prevPosX = pos.getX();
        this.prevPosY = pos.getY();
        this.prevPosZ = pos.getZ();
    }

    @Override
    public final void setLastTickPos(Vec3 pos) {
        this.lastTickPosX = pos.getX();
        this.lastTickPosY = pos.getY();
        this.lastTickPosZ = pos.getZ();
    }

    @Override
    public final void setRotations(Vec2 rotations) {
        this.rotationYaw = rotations.getX();
        this.rotationPitch = rotations.getY();
    }

    @Override
    public final void setPrevRotations(Vec2 rotations) {
        this.prevRotationYaw = rotations.getX();
        this.prevRotationPitch = rotations.getY();
    }

    @Override
    public final void setMotion(Vec3 motion) {
        this.motionX = pos.getX();
        this.motionY = pos.getY();
        this.motionZ = pos.getZ();
    }

    @Override
    public final Vec3 getPos() {
        if (pos == null)
            pos = new Vec3();

        return pos.setX(posX).setY(posY).setZ(posZ);
    }

    @Override
    public final Vec3 getPrevPos() {
        if (prevPos == null)
            prevPos = new Vec3();

        return prevPos.setX(prevPosX).setY(prevPosY).setZ(prevPosZ);
    }

    @Override
    public final Vec3 getLastTickPos() {
        if (lastTickPos == null)
            lastTickPos = new Vec3();

        return lastTickPos.setX(lastTickPosX).setY(lastTickPosY).setZ(lastTickPosZ);
    }

    @Override
    public final Vec3 getHeadPos() {
        if (headPos == null)
            headPos = new Vec3();

        return headPos.setX(posX).setY(posY + getEyeHeight()).setZ(posZ);
    }

    @Override
    public final Vec2 getRotations() {
        if (rotation == null)
            rotation = new Vec2();

        return rotation.setX(rotationYaw).setY(rotationPitch);
    }

    @Override
    public  final Vec2 getPrevRotations() {
        if (prevRotation == null)
            prevRotation = new Vec2();

        return prevRotation.setX(prevRotationYaw).setY(prevRotationPitch);
    }

    @Override
    public final Vec3 getMotion() {
        if (motion == null)
            motion = new Vec3();

        return motion.setX(motionX).setY(motionY).setZ(motionZ);
    }

    @Override
    public final Vec3 interpolate(float ticks) {
        return this.getLastTickPos().add(this.getPos().sub(this.getLastTickPos()).scale(ticks));
    }
}
