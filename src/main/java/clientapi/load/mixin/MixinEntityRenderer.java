/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.render.RenderScreenEvent;
import clientapi.event.defaults.game.render.RenderWorldEvent;
import clientapi.util.render.camera.Camera;
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
    private void getFOVModifier(float partialTicks, boolean useFOVSetting, CallbackInfoReturnable<Float> ci) {
        if (Camera.isCapturing())
            ci.setReturnValue(90.0F);
    }

    @Inject(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "net/minecraft/profiler/Profiler.endStartSection(Ljava/lang/String;)V", args = { "ldc=gui" }))
    private void updateCameraAndRender(float partialTicks, long nanoTime, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new RenderScreenEvent(partialTicks));
    }

    @Inject(method = "renderWorldPass", at = @At(value = "INVOKE_ASSIGN", target = "net/minecraft/profiler/Profiler.endStartSection(Ljava/lang/String;)V", args = { "ldc=hand" }))
    private void onStartHand(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new RenderWorldEvent(partialTicks, pass));
    }
}
