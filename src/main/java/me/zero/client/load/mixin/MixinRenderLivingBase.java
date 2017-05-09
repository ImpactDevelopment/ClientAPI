package me.zero.client.load.mixin;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.EntityRenderEvent;
import me.zero.client.api.event.defaults.LayerRenderEvent;
import me.zero.client.api.event.type.EventState;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by Brady on 4/27/2017.
 */
@Mixin(RenderLivingBase.class)
public class MixinRenderLivingBase {

    @Inject(method = "doRender", at = @At("HEAD"))
    public void doRenderPre(EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        EventManager.post(new EntityRenderEvent(EventState.PRE, (RenderLivingBase) (Object) this, entity));
    }

    @Inject(method = "doRender", at = @At("RETURN"))
    public void doRenderPost(EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        EventManager.post(new EntityRenderEvent(EventState.POST, (RenderLivingBase) (Object) this, entity));
    }

    @Redirect(method = "renderLayers", at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/entity/layers/LayerRenderer.doRenderLayer(Lnet/minecraft/entity/EntityLivingBase;FFFFFFF)V"))
    @SuppressWarnings("unchecked")
    public void doRenderLayer(LayerRenderer renderer, EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {
        LayerRenderEvent event = new LayerRenderEvent(entitylivingbaseIn, renderer);
        EventManager.post(event);
        if (!event.isCancelled())
            renderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
    }
}
