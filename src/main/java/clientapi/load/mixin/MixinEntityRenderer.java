/*
 * Copyright 2018 ImpactDevelopment
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
import clientapi.event.defaults.game.render.HudOverlayEvent;
import clientapi.event.defaults.game.render.RenderHudEvent;
import clientapi.event.defaults.game.render.RenderScreenEvent;
import clientapi.event.defaults.game.render.RenderWorldEvent;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.block.material.Material.AIR;
import static net.minecraft.block.material.Material.WATER;
import static org.spongepowered.asm.lib.Opcodes.GETFIELD;

/**
 * @author Brady
 * @since 4/27/2017
 */
@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Inject(
            method = "updateCameraAndRender",
            at = @At(
                    value = "FIELD",
                    opcode = GETFIELD,
                    target = "net/minecraft/client/renderer/OpenGlHelper.shadersSupported:Z",
                    shift = At.Shift.BEFORE
            )
    )
    private void updateCameraAndRender(float partialTicks, long nanoTime, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new RenderScreenEvent(partialTicks));
    }

    @Inject(
            method = "renderWorldPass",
            at = @At(
                    value = "INVOKE_STRING",
                    target = "net/minecraft/profiler/Profiler.endStartSection(Ljava/lang/String;)V",
                    args = "ldc=hand"
            )
    )
    private void onStartHand(int pass, float partialTicks, long finishTimeNano, CallbackInfo ci) {
        ClientAPI.EVENT_BUS.post(new RenderWorldEvent(partialTicks, pass));
    }

    @Redirect(
            method = "updateCameraAndRender",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/gui/GuiIngame.renderGameOverlay(F)V"
            )
    )
    private void updateCameraAndRender$renderGameOverlay(GuiIngame guiIngame, float partialTicks) {
        guiIngame.renderGameOverlay(partialTicks);
        ClientAPI.EVENT_BUS.post(new RenderHudEvent(partialTicks));
    }

    @Inject(
            method = "hurtCameraEffect",
            at = @At("HEAD"),
            cancellable = true
    )
    private void hurtCameraEffect(float partialTicks, CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.HURTCAM);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Redirect(
            method = "setupFog",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/state/IBlockState;getMaterial()Lnet/minecraft/block/material/Material;"
            )
    )
    private Material setupFog$getMaterial(IBlockState iblockstate) {
        Material m = iblockstate.getMaterial();

        HudOverlayEvent event = new HudOverlayEvent(m == WATER ? HudOverlayEvent.Type.WATER : HudOverlayEvent.Type.LAVA);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            return AIR;

        return m;
    }
}
