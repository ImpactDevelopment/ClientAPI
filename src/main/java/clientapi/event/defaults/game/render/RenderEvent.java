package clientapi.event.defaults.game.render;

import clientapi.util.interfaces.Helper;

/**
 * Abstract class to represent render events that have a partialTicks parameter.
 *
 * @author Brady
 * @since 12/1/2017 9:54 AM
 */
abstract class RenderEvent implements Helper {

    /**
     * The render partial ticks
     */
    private final float partialTicks;

    public RenderEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    /**
     * @return The render partial ticks
     */
    public final float getPartialTicks() {
        return this.partialTicks;
    }
}
