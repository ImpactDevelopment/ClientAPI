package me.zero.client.api.gui.widget.data;

import me.zero.client.api.gui.widget.WidgetHelper;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.math.Vec2;

/**
 * Default implementations of {@code WidgetPos}
 *
 * @see WidgetPos`
 *
 * @author Brady
 * @since 5/28/2017 10:00 AM
 */
public enum DefaultWidgetPos implements WidgetPos, Helper {

    LEFT_TOP(0.0F, 0.0F, 0.0F),
    LEFT_MIDDLE(0.0F, 0.5F, -0.5F),
    LEFT_BOTTOM(0.0F, 1.0F, -1.0F),

    CENTER_TOP(0.5F, 0.0F, 0.0F),
    CENTER_MIDDLE(0.5F, 0.5F, -0.5F),
    CENTER_BOTTOM(0.5F, 1.0F, -1.0F),

    RIGHT_TOP(1.0F, 0.0F, 0.0F),
    RIGHT_MIDDLE(1.0F, 0.5F, -0.5F),
    RIGHT_BOTTOM(1.0F, 1.0F, -1.0F);

    private final Vec2 pos;
    private final Vec2 padding;
    private final float offset;

    DefaultWidgetPos(float x, float y, float offset) {
        this.pos = new Vec2(x, y);
        this.padding = WidgetHelper.getPadding(pos);
        this.offset = offset;
    }

    @Override
    public final Vec2 getPos() {
        return this.pos;
    }

    @Override
    public final Vec2 getPadding() {
        return this.padding;
    }

    @Override
    public final float getOffset() {
        return this.offset;
    }
}
