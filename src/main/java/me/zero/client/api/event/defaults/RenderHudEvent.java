package me.zero.client.api.event.defaults;

import net.minecraft.client.gui.GuiIngame;

/**
 * Called after the in-game overlay is finished rendering.
 *
 * @see GuiIngame#renderGameOverlay(float)
 *
 * @author Brady
 * @since 2/6/2017 12:00 PM
 */
public final class RenderHudEvent {

    /**
     * The render partial ticks
     */
    private final float partialTicks;

    public RenderHudEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    /**
     * @return The render partial ticks
     */
    public final float getPartialTicks() {
        return this.partialTicks;
    }
}
