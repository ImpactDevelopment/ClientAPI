package me.zero.client.load.mixin;

import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.RenderScreenEvent;
import me.zero.client.api.util.render.camera.Camera;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Inject(method = "getFOVModifier", at = @At("HEAD"), cancellable = true)
    public void getFOVModifier(float partialTicks, boolean useFOVSetting, CallbackInfoReturnable<Float> ci) {
        if (Camera.isCapturing()) {
            ci.setReturnValue(90.0F);
        }
    }

    @Inject(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "net/minecraft/profiler/Profiler.endStartSection(Ljava/lang/String;)V"))
    public void updateCameraAndRender(float partialTicks, long nanoTime, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new RenderScreenEvent(partialTicks));
    }
}
