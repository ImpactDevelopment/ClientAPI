package me.zero.client.load.mixin;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.EntityRenderEvent;
import me.zero.client.api.event.defaults.LayerRenderEvent;
import me.zero.client.api.event.type.EventState;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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

    @Inject(method = "renderLayers", at = @At("HEAD"), cancellable = true)
    public void renderLayers(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn, CallbackInfo ci) {
        LayerRenderEvent event = new LayerRenderEvent(entitylivingbaseIn);
        EventManager.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}
