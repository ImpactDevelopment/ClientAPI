package me.zero.client.api.event.defaults;

import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.wrapper.IMinecraft;

/**
 * Called at the end of EntityRenderer#renderWorldPass(int, float, long)
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
 */
public final class Render3DEvent implements Helper {

    private final float partialTicks;

    private Render3DEvent() {
        this.partialTicks = ((IMinecraft) mc).getTimer().renderPartialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
}
