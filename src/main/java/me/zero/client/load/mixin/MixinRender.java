package me.zero.client.load.mixin;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.TeamColorEvent;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Brady
 * @since 5/21/2017 11:48 AM
 */
@Mixin(Render.class)
public class MixinRender {

    @Inject(method = "getTeamColor", at = @At("HEAD"), cancellable = true)
    public void getTeamColor(Entity entityIn, CallbackInfoReturnable<Integer> ci) {
        TeamColorEvent event = new TeamColorEvent(entityIn);
        EventManager.post(event);
        if (event.isCancelled())
            ci.setReturnValue(event.getColor());
    }
}
