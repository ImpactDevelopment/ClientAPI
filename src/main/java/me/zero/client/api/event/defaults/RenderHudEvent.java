package me.zero.client.api.event.defaults;

/**
 * Called after GuiIngame#renderGameOverlay(float) is called.
 *
 * @since 1.0
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
     * @since 1.0
     *
     * @return The render partial ticks
     */
    public final float getPartialTicks() {
        return this.partialTicks;
    }
}
