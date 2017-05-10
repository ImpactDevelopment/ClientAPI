package me.zero.client.api.event.defaults;

import me.zero.client.api.util.interfaces.Helper;

/**
 * Called at the end of EntityRenderer#renderWorldPass(int, float, long)
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public final class Render3DEvent implements Helper {

    /**
     * The render partial ticks
     */
    private final float partialTicks;

    public Render3DEvent() {
        this.partialTicks = mca.getTimer().renderPartialTicks;
    }

    /**
     * @return The render partial ticks
     */
    public final float getPartialTicks() {
        return this.partialTicks;
    }
}
