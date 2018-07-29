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
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * @author Brady
 * @since 7/28/2018 5:50 PM
 */
@Mixin(FogRenderer.class)
public class MixinFogRenderer {

    @Redirect(method = "setupFog", at = @At(value = "INVOKE", target = "net/minecraft/fluid/IFluidState.func_206884_a(Lnet/minecraft/tags/Tag;)Z"))
    private boolean setupFog$getMaterial(IFluidState fluidState, Tag<Fluid> fluidTag) {
        HudOverlayEvent event = null;

        if (fluidTag == FluidTags.field_206959_a)
            event = new HudOverlayEvent(HudOverlayEvent.Type.WATER);

        if (fluidTag == FluidTags.field_206960_b)
            event = new HudOverlayEvent(HudOverlayEvent.Type.LAVA);

        if (event != null) {
            ClientAPI.EVENT_BUS.post(event);
            if (event.isCancelled())
                return false;
        }

        return fluidState.func_206884_a(fluidTag);
    }
}
