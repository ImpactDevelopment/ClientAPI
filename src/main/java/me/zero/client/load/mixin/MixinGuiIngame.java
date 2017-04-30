package me.zero.client.load.mixin;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.Render2DEvent;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Brady on 4/27/2017.
 */
@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    public void renderGameOverlay(float partialTicks, CallbackInfo ci) {
        EventManager.post(new Render2DEvent(partialTicks));
    }
}
