package me.zero.client.api.event.defaults;

/**
 * Called at the gui section in EntityRenderer#updateCameraAndRender(float, long)
 *
 * @since 1.0
 *
 * @author Brady
 * @since 4/30/2017 12:00 PM
 */
public final class RenderScreenEvent {

    /**
     * The render partial ticks
     */
    private final float partialTicks;

    public RenderScreenEvent(float partialTicks) {
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
