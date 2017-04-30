package me.zero.client.load.mixin;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.LivingUpdateEvent;
import me.zero.client.api.event.defaults.MotionUpdateEvent;
import me.zero.client.api.event.defaults.MoveEvent;
import me.zero.client.api.event.defaults.UpdateEvent;
import me.zero.client.api.event.type.EventState;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Brady on 4/27/2017.
 */
@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP extends MixinEntity {

    @Inject(method = "onUpdate", at = @At("HEAD"))
    public void onUpdate(CallbackInfo ci) {
        EventManager.post(new UpdateEvent());
    }

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    public void onLivingUpdatePre(CallbackInfo ci) {
        EventManager.post(new LivingUpdateEvent(EventState.PRE));
    }

    @Inject(method = "onLivingUpdate", at = @At("RETURN"))
    public void onLivingUpdatePost(CallbackInfo ci) {
        EventManager.post(new LivingUpdateEvent(EventState.POST));
    }

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "net/minecraft/client/entity/AbstractClientPlayer.move(Lnet/minecraft/entity/MoverType;DDD)V"))
    public void move(AbstractClientPlayer player, MoverType type, double x, double y, double z) {
        MoveEvent event = new MoveEvent(type, x, y, z);
        EventManager.post(event);
        if (event.isCancelled())
            return;

        super.move(type, event.getX(), event.getY(), event.getZ());
    }

    @Inject(method = "onUpdateWalkingPlayer", at = @At("HEAD"))
    public void onUpdateWalkingPlayerPre(CallbackInfo ci) {
        EventManager.post(new MotionUpdateEvent(EventState.PRE));
        MotionUpdateEvent.apply();
    }

    @Inject(method = "onUpdateWalkingPlayer", at = @At("RETURN"))
    public void onUpdateWalkingPlayerPost(CallbackInfo ci) {
        MotionUpdateEvent.reset();
        EventManager.post(new MotionUpdateEvent(EventState.POST));
    }
}
