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

package clientapi.event.defaults.game.render;

import clientapi.util.interfaces.Helper;
import me.zero.alpine.type.Cancellable;
import net.minecraft.client.renderer.FirstPersonRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;

/**
 * Called when FirstPersonRenderer#renderItemInFirstPerson is called.
 * If cancelled, the item isn't rendered.
 *
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
public final class FirstPersonItemRenderEvent extends Cancellable implements Helper {

    /**
     * Instance of the ItemRenderer
     */
    private final FirstPersonRenderer firstPersonRenderer;

    /**
     * Render partial ticks
     */
    private final float partialTicks;

    /**
     * Hand that is holding the item
     */
    private final EnumHand hand;

    /**
     * Hand side that is holding the item
     */
    private final EnumHandSide handSide;

    /**
     * Item swing progress
     */
    private final float swingProgress;

    /**
     * Item stack being rendererd
     */
    private final ItemStack stack;

    /**
     * Recharge progress of the item
     */
    private final float rechargeProgress;

    public FirstPersonItemRenderEvent(FirstPersonRenderer firstPersonRenderer, float partialTicks, EnumHand hand, float swingProgress, ItemStack stack, float rechargeProgress) {
        this.firstPersonRenderer = firstPersonRenderer;
        this.partialTicks = partialTicks;
        this.hand = hand;
        this.swingProgress = swingProgress;
        this.stack = stack;
        this.rechargeProgress = rechargeProgress;

        this.handSide = hand == EnumHand.MAIN_HAND ? mc.player.getPrimaryHand() : mc.player.getPrimaryHand().opposite();
    }

    /**
     * @return The instance of the FirstPersonRenderer
     */
    public final FirstPersonRenderer getFirstPersonRenderer() {
        return this.firstPersonRenderer;
    }

    /**
     * @return The current render partial ticks
     */
    public final float getPartialTicks() {
        return this.partialTicks;
    }

    /**
     * @return The hand holding the item
     */
    public final EnumHand getHand() {
        return this.hand;
    }

    /**
     * @return The side of the screen that the holding hand is represented by
     */
    public final EnumHandSide getHandSide() {
        return this.handSide;
    }

    /**
     * @return The swing progress of the item being renderered
     */
    public final float getSwingProgress() {
        return this.swingProgress;
    }

    /**
     * @return The itemstack being rendered
     */
    public final ItemStack getStack() {
        return this.stack;
    }

    /**
     * @return The recharge progress of the item being rendered.
     */
    public final float getRechargeProgress() {
        return this.rechargeProgress;
    }
}
