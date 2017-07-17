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

package me.zero.client.api.event.defaults.game;

import me.zero.alpine.type.Cancellable;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;

/**
 * Called when ItemRenderer#renderItemInFirstPerson is called.
 * If cancelled, the item isn't
 *
 *
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
public final class ItemRenderEvent extends Cancellable implements Helper {

    /**
     * Instance of the ItemRenderer
     */
    private final ItemRenderer itemRenderer;

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

    public ItemRenderEvent(ItemRenderer itemRenderer, float partialTicks, EnumHand hand, float swingProgress, ItemStack stack, float rechargeProgress) {
        this.itemRenderer = itemRenderer;
        this.partialTicks = partialTicks;
        this.hand = hand;
        this.swingProgress = swingProgress;
        this.stack = stack;
        this.rechargeProgress = rechargeProgress;

        this.handSide = hand == EnumHand.MAIN_HAND ? mc.player.getPrimaryHand() : mc.player.getPrimaryHand().opposite();
    }

    /**
     * @return The instance of the ItemRenderer
     */
    public final ItemRenderer getItemRenderer() {
        return itemRenderer;
    }

    /**
     * @return The current render partial ticks
     */
    public final float getPartialTicks() {
        return partialTicks;
    }

    /**
     * @return The hand holding the item
     */
    public final EnumHand getHand() {
        return hand;
    }

    /**
     * @return The side of the screen that the holding hand is represented by
     */
    public final EnumHandSide getHandSide() {
        return handSide;
    }

    /**
     * @return The swing progress of the item being renderered
     */
    public final float getSwingProgress() {
        return swingProgress;
    }

    /**
     * @return The itemstack being rendered
     */
    public final ItemStack getStack() {
        return stack;
    }

    /**
     * @return The recharge progress of the item being rendered.
     */
    public final float getRechargeProgress() {
        return rechargeProgress;
    }
}
