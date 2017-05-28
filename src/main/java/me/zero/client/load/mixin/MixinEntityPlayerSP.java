package me.zero.client.load.mixin;

import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.LivingUpdateEvent;
import me.zero.client.api.event.defaults.MotionUpdateEvent;
import me.zero.client.api.event.defaults.MoveEvent;
import me.zero.client.api.event.defaults.UpdateEvent;
import me.zero.event.type.EventState;
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
    @Shadow public abstract boolean isCurrentViewEntity();

    @Inject(method = "onUpdate", at = @At("HEAD"))
    public void onUpdate(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new UpdateEvent());
    }

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    public void onLivingUpdatePre(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LivingUpdateEvent(EventState.PRE));
    }

    @Inject(method = "onLivingUpdate", at = @At("RETURN"))
    public void onLivingUpdatePost(CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new LivingUpdateEvent(EventState.POST));
    }

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "net/minecraft/client/entity/AbstractClientPlayer.move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(AbstractClientPlayer player, MoverType type, double x, double y, double z) {
        MoveEvent event = new MoveEvent(type, x, y, z);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            return;

        super.move(type, event.getX(), event.getY(), event.getZ());
    }

    @Overwrite
    public void onUpdateWalkingPlayer() {
        MotionUpdateEvent pre = new MotionUpdateEvent(EventState.PRE);
        ClientAPI.EVENT_BUS.post(pre);

        boolean flag = this.isSprinting();

        if (flag != this.serverSprintState) {
            if (flag) {
                this.connection.sendPacket(new CPacketEntityAction((EntityPlayerSP) (Object) this, CPacketEntityAction.Action.START_SPRINTING));
            } else {
                this.connection.sendPacket(new CPacketEntityAction((EntityPlayerSP) (Object) this, CPacketEntityAction.Action.STOP_SPRINTING));
            }

            this.serverSprintState = flag;
        }

        boolean flag1 = this.isSneaking();

        if (flag1 != this.serverSneakState) {
            if (flag1) {
                this.connection.sendPacket(new CPacketEntityAction((EntityPlayerSP) (Object) this, CPacketEntityAction.Action.START_SNEAKING));
            } else {
                this.connection.sendPacket(new CPacketEntityAction((EntityPlayerSP) (Object) this, CPacketEntityAction.Action.STOP_SNEAKING));
            }

            this.serverSneakState = flag1;
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
            double d3 = (double) (pYaw - this.lastReportedYaw);
            double d4 = (double) (pPitch - this.lastReportedPitch);

            ++this.positionUpdateTicks;
            boolean pUpdate = d0 * d0 + d1 * d1 + d2 * d2 > 9.0E-4D || this.positionUpdateTicks >= 20;
            boolean rUpdate = d3 != 0.0D || d4 != 0.0D;

            if (this.isRiding()) {
                this.connection.sendPacket(new CPacketPlayer.PositionRotation(this.motionX, -999.0D, this.motionZ, this.rotationYaw, this.rotationPitch, this.onGround));
                pUpdate = false;
            } else if (pUpdate && rUpdate) {
                this.connection.sendPacket(new CPacketPlayer.PositionRotation(pX, pY, pZ, pYaw, pPitch, pGround));
            } else if (pUpdate) {
                this.connection.sendPacket(new CPacketPlayer.Position(pX, pY, pZ, pGround));
            } else if (rUpdate) {
                this.connection.sendPacket(new CPacketPlayer.Rotation(pYaw, pPitch, pGround));
            } else if (this.prevOnGround != pGround) {
                this.connection.sendPacket(new CPacketPlayer(pGround));
            }

            if (pUpdate) {
                this.lastReportedPosX = pX;
                this.lastReportedPosY = pY;
                this.lastReportedPosZ = pZ;
                this.positionUpdateTicks = 0;
            }

            if (rUpdate) {
                this.lastReportedYaw = pYaw;
                this.lastReportedPitch = pPitch;
            }

            this.prevOnGround = pGround;
            this.autoJumpEnabled = this.mc.gameSettings.autoJump;
        }

        ClientAPI.EVENT_BUS.post(new MotionUpdateEvent(EventState.POST));
    }
}
