/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.load.mixin;

import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.game.entity.LivingUpdateEvent;
import me.zero.client.api.event.defaults.game.entity.MotionUpdateEvent;
import me.zero.client.api.event.defaults.game.entity.MoveEvent;
import me.zero.client.api.event.defaults.game.core.UpdateEvent;
import me.zero.alpine.type.EventState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.MoverType;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends MixinEntity {

    @Shadow @Final public NetHandlerPlayClient connection;
    @Shadow private double lastReportedPosX;
    @Shadow private double lastReportedPosY;
    @Shadow private double lastReportedPosZ;
    @Shadow private float lastReportedYaw;
    @Shadow private float lastReportedPitch;
    @Shadow private boolean serverSprintState;
    @Shadow private boolean serverSneakState;
    @Shadow private boolean prevOnGround;
    @Shadow private int positionUpdateTicks;
    @Shadow protected Minecraft mc;
    @Shadow private boolean autoJumpEnabled;

    @Shadow public abstract boolean isSneaking();
    @Shadow protected abstract boolean isCurrentViewEntity();

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onUpdate(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new UpdateEvent());
    }

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void onLivingUpdatePre(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LivingUpdateEvent(EventState.PRE));
    }

    @Inject(method = "onLivingUpdate", at = @At("RETURN"))
    private void onLivingUpdatePost(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LivingUpdateEvent(EventState.POST));
    }

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "net/minecraft/client/entity/AbstractClientPlayer.move(Lnet/minecraft/entity/MoverType;DDD)V"))
    private void move(AbstractClientPlayer player, MoverType type, double x, double y, double z) {
        MoveEvent event = new MoveEvent(type, x, y, z);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            return;

        super.move(type, event.getX(), event.getY(), event.getZ());
    }

    /**
     * @author Brady
     */
    @Overwrite
    public void onUpdateWalkingPlayer() {
        EntityPlayerSP _this = (EntityPlayerSP) (Object) this;

        MotionUpdateEvent pre = new MotionUpdateEvent(EventState.PRE);
        ClientAPI.EVENT_BUS.post(pre);

        boolean clientSprintState = this.isSprinting();
        if (clientSprintState != this.serverSprintState) {
            this.connection.sendPacket(new CPacketEntityAction(_this, clientSprintState ? CPacketEntityAction.Action.START_SPRINTING : CPacketEntityAction.Action.STOP_SPRINTING));
            this.serverSprintState = clientSprintState;
        }

        boolean clientSneakState = this.isSneaking();
        if (clientSneakState != this.serverSneakState) {
            this.connection.sendPacket(new CPacketEntityAction(_this, clientSneakState ? CPacketEntityAction.Action.START_SNEAKING : CPacketEntityAction.Action.STOP_SNEAKING));
            this.serverSneakState = clientSneakState;
        }

        if (this.isCurrentViewEntity()) {

            double pX = pre.getX();
            double pY = pre.getY();
            double pZ = pre.getZ();
            float pYaw = pre.getYaw();
            float pPitch = pre.getPitch();
            boolean pGround = pre.isOnGround();

            double d0 = pX - this.lastReportedPosX;
            double d1 = pY - this.lastReportedPosY;
            double d2 = pZ - this.lastReportedPosZ;
            double d3 = pYaw - this.lastReportedYaw;
            double d4 = pPitch - this.lastReportedPitch;

            boolean position = d0 * d0 + d1 * d1 + d2 * d2 > 9.0E-4D || ++this.positionUpdateTicks >= 20;
            boolean rotation = d3 != 0.0D || d4 != 0.0D;

            if (this.isRiding()) {
                this.connection.sendPacket(new CPacketPlayer.PositionRotation(this.motionX, -999.0D, this.motionZ, this.rotationYaw, this.rotationPitch, this.onGround));
                position = false;
            } else if (position && rotation) {
                this.connection.sendPacket(new CPacketPlayer.PositionRotation(pX, pY, pZ, pYaw, pPitch, pGround));
            } else if (position) {
                this.connection.sendPacket(new CPacketPlayer.Position(pX, pY, pZ, pGround));
            } else if (rotation) {
                this.connection.sendPacket(new CPacketPlayer.Rotation(pYaw, pPitch, pGround));
            } else if (this.prevOnGround != pGround) {
                this.connection.sendPacket(new CPacketPlayer(pGround));
            }

            if (position) {
                this.lastReportedPosX = pX;
                this.lastReportedPosY = pY;
                this.lastReportedPosZ = pZ;
                this.positionUpdateTicks = 0;
            }

            if (rotation) {
                this.lastReportedYaw = pYaw;
                this.lastReportedPitch = pPitch;
            }

            this.prevOnGround = pGround;
            this.autoJumpEnabled = this.mc.gameSettings.autoJump;
        }

        ClientAPI.EVENT_BUS.post(new MotionUpdateEvent(EventState.POST));
    }
}
