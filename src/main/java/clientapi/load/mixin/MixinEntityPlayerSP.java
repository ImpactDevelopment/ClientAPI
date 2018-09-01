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
import clientapi.event.defaults.game.core.UpdateEvent;
import clientapi.event.defaults.game.entity.local.LivingUpdateEvent;
import clientapi.event.defaults.game.entity.local.MotionUpdateEvent;
import clientapi.event.defaults.game.entity.local.MoveEvent;
import clientapi.event.defaults.game.misc.ChatEvent;
import me.zero.alpine.type.EventState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.MoverType;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 4/27/2017
 */
@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends MixinEntityLivingBase {

    @Shadow protected Minecraft mc;

    private MotionUpdateEvent preMotionUpdateEvent;

    @Inject(
            method = "onUpdate",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onPreUpdate(CallbackInfo ci) {
        UpdateEvent preUpdateEvent = new UpdateEvent(EventState.PRE);
        ClientAPI.EVENT_BUS.post(preUpdateEvent);
        if (preUpdateEvent.isCancelled())
            ci.cancel();
    }

    @Inject(
            method = "onUpdate",
            at = @At("RETURN")
    )
    private void onPostUpdate(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new UpdateEvent(EventState.POST));
    }

    @Inject(
            method = "onLivingUpdate",
            at = @At("HEAD")
    )
    private void onPreLivingUpdate(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LivingUpdateEvent(EventState.PRE));
    }

    @Inject(
            method = "onLivingUpdate",
            at = @At("RETURN")
    )
    private void onPostLivingUpdate(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LivingUpdateEvent(EventState.POST));
    }

    @Redirect(
            method = "move",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/entity/AbstractClientPlayer.move(Lnet/minecraft/entity/MoverType;DDD)V"
            )
    )
    private void move$move(AbstractClientPlayer player, MoverType type, double x, double y, double z) {
        MoveEvent event = new MoveEvent(type, x, y, z);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            return;

        super.move(type, event.getX(), event.getY(), event.getZ());
    }

    @Redirect(
            method = "sendChatMessage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket(Lnet/minecraft/network/Packet;)V"
            )
    )
    private void sendChatPacket(NetHandlerPlayClient connection, Packet<?> packet) {
        ChatEvent event = new ChatEvent.Send(((CPacketChatMessage) packet).getMessage());
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled()) return;

        connection.sendPacket(new CPacketChatMessage(event.getRawMessage()));
    }

    @Inject(
            method = "onUpdateWalkingPlayer",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onPreUpdateWalking(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(this.preMotionUpdateEvent = new MotionUpdateEvent(EventState.PRE));
        if (this.preMotionUpdateEvent.isCancelled())
            ci.cancel();
    }

    @Inject(
            method = "onUpdateWalkingPlayer",
            at = @At("RETURN")
    )
    private void onPostUpdateWalking(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new MotionUpdateEvent(EventState.POST));
        this.preMotionUpdateEvent = null;
    }

    @Redirect(
            method = "onUpdateWalkingPlayer",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/entity/EntityPlayerSP;posX:D"
            )
    )
    private double injectPosX(EntityPlayerSP self) {
        return this.preMotionUpdateEvent.getX();
    }

    @Redirect(
            method = "onUpdateWalkingPlayer",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/util/math/AxisAlignedBB;minY:D"
            )
    )
    private double injectPosY(AxisAlignedBB self) {
        return this.preMotionUpdateEvent.getY();
    }

    @Redirect(
            method = "onUpdateWalkingPlayer",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/entity/EntityPlayerSP;posZ:D"
            )
    )
    private double injectPosZ(EntityPlayerSP self) {
        return this.preMotionUpdateEvent.getZ();
    }

    @Redirect(
            method = "onUpdateWalkingPlayer",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/entity/EntityPlayerSP;rotationYaw:F"
            )
    )
    private float injectYaw(EntityPlayerSP self) {
        return this.preMotionUpdateEvent.getYaw();
    }

    @Redirect(
            method = "onUpdateWalkingPlayer",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/entity/EntityPlayerSP;rotationPitch:F"
            )
    )
    private float injectPitch(EntityPlayerSP self) {
        return this.preMotionUpdateEvent.getPitch();
    }

    @Redirect(
            method = "onUpdateWalkingPlayer",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/entity/EntityPlayerSP;onGround:Z"
            )
    )
    private boolean injectOnGround(EntityPlayerSP self) {
        return this.preMotionUpdateEvent.isOnGround();
    }
}
