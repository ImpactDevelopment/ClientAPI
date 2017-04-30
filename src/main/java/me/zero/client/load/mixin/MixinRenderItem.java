package me.zero.client.load.mixin;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.GlintEffectEvent;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Brady on 4/27/2017.
 */
@Mixin(RenderItem.class)
public class MixinRenderItem {

    @Inject(method = "renderEffect", at = @At("HEAD"), cancellable = true)
    public void renderEffect(IBakedModel model, CallbackInfo ci) {
        GlintEffectEvent event = new GlintEffectEvent(GlintEffectEvent.GlintTarget.ITEM);
        EventManager.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}
