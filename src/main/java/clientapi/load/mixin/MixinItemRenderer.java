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
import clientapi.event.defaults.game.render.RenderItemEvent;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 4/27/2017
 */
@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer {

    @Inject(
            method = "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderItemInFirstPerson(AbstractClientPlayer p_187457_1_, float partialTicks, float pitch, EnumHand hand, float swingProgress, ItemStack stack, float rechargeProgress, CallbackInfo ci) {
        RenderItemEvent event = new RenderItemEvent((ItemRenderer) (Object) this, partialTicks, hand, swingProgress, stack, rechargeProgress);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }

    @Inject(
            method = "renderFireInFirstPerson",
            at = @At("HEAD"),
            cancellable = true
    )
    private void renderFireInFirstPerson(CallbackInfo ci) {
        HudOverlayEvent event = new HudOverlayEvent(HudOverlayEvent.Type.FIRE);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.cancel();
    }
}
