/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.load.mixin;

import me.zero.client.api.ClientAPI;
import me.zero.client.api.event.defaults.game.render.LayerRenderEvent;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(RenderLivingBase.class)
public class MixinRenderLivingBase {

    @Redirect(method = "renderLayers", at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/entity/layers/LayerRenderer.doRenderLayer(Lnet/minecraft/entity/EntityLivingBase;FFFFFFF)V"))
    @SuppressWarnings("unchecked")
    public void doRenderLayer(LayerRenderer renderer, EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {
        LayerRenderEvent event = new LayerRenderEvent(entitylivingbaseIn, renderer);
        ClientAPI.EVENT_BUS.post(event);
        if (!event.isCancelled())
            renderer.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
    }
}
