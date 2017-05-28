package me.zero.client.load.mixin;

import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.EntityDeathEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(EntityLivingBase.class)
public class MixinEntityLivingBase {

    @Inject(method = "onDeath", at = @At("HEAD"))
    public void onDeath(DamageSource cause, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new EntityDeathEvent((EntityLivingBase) (Object) this, cause));
    }
}
