package me.zero.client.api.gui.widget;

import me.zero.client.api.util.math.Vec2;

/**
 * Contains some methods to make required
 * calculations for widgets.
 *
 * @author Brady
 * @since 5/28/2017 10:00 AM
 */
public final class WidgetHelper {

    private WidgetHelper() {}

    /**
     * Calculates the padding that should be applied
     * to the given widget position (as a Vec2)
     *
     * @param pos The widget position as a Vec2
     * @return The padding
     */
    public static Vec2 getPadding(Vec2 pos) {
        return new Vec2(getPadding(pos.getX()), getPadding(pos.getY()));
    }

    /**
     * Gets the padding applied from the specified screen pos.
     *
     * @param pos The screen pos (0.0/left to 1.0/right)
     * @return The calculated padding
     */
    private static float getPadding(float pos) {
        return pos == 0.0F ? 1.0F : pos == 1.0F ? -1.0F : 0.0F;
    }
}
