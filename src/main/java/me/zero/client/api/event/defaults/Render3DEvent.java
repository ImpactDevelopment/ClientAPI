package me.zero.client.api.event.defaults;

import me.zero.client.api.util.interfaces.Helper;

/**
 * Called at the end of EntityRenderer#renderWorldPass(int, float, long)
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
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
     * @since 1.0
     *
     * @return The render partial ticks
     */
    public final float getPartialTicks() {
        return this.partialTicks;
    }
}
