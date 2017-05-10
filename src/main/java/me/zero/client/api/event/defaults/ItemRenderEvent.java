package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;
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
 * @since 1.0
 *
 * @author Brady
 * @since 4/8/2017 12:00PM
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
     * @since 1.0
     *
     * @return The instance of the ItemRenderer
     */
    public final ItemRenderer getItemRenderer() {
        return itemRenderer;
    }

    /**
     * @since 1.0
     *
     * @return The current render partial ticks
     */
    public final float getPartialTicks() {
        return partialTicks;
    }

    /**
     * @since 1.0
     *
     * @return The hand holding the item
     */
    public final EnumHand getHand() {
        return hand;
    }

    /**
     * @since 1.0
     *
     * @return The side of the screen that the holding hand is represented by
     */
    public final EnumHandSide getHandSide() {
        return handSide;
    }

    /**
     * @since 1.0
     *
     * @return The swing progress of the item being renderered
     */
    public final float getSwingProgress() {
        return swingProgress;
    }

    /**
     * @since 1.0
     *
     * @return The itemstack being rendered
     */
    public final ItemStack getStack() {
        return stack;
    }

    /**
     * @since 1.0
     *
     * @return The recharge progress of the item being rendered.
     */
    public final float getRechargeProgress() {
        return rechargeProgress;
    }
}
