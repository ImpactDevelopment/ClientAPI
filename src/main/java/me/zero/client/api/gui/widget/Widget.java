package me.zero.client.api.gui.widget;

import me.zero.client.api.gui.widget.data.WidgetAlignment;
import me.zero.client.api.gui.widget.data.WidgetPos;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

/**
 * A widget, an element rendered on your HUD
 *
 * @author Brady
 * @since 5/28/2017 10:00 AM
 */
public interface Widget extends Helper {

    /**
     * Renders this widget on a screen with the specified
     * scaled resolution using the specified font renderer.
     *
     * @param font The font that used to render text
     * @param sr The screen size
     */
    void render(FontRenderer font, ScaledResolution sr);

    /**
     * Sets this widget's position on the screen
     *
     * @param pos The new position
     * @return This widget
     */
    Widget setPos(WidgetPos pos);

    /**
     * Sets this widget's horizontal alignment
     *
     * @param alignment The new alignment
     * @return This widget
     */
    Widget setAlignment(WidgetAlignment alignment);

    /**
     * Sets the width of the widget. This should be
     * called after the widget has been rendered to
     * let the handler know the updated bounds of the
     * widget.
     *
     * @param width The new width
     * @return This widget
     */
    Widget setWidth(float width);

    /**
     * Sets the height of the widget. This should be
     * called after the widget has been rendered to
     * let the handler know the updated bounds of the
     * widget.
     *
     * @param height The new height
     * @return This widget
     */
    Widget setHeight(float height);

    /**
     * Sets the visibility flag of the widget. If
     * this is set to false, then the widget isn't
     * rendered.
     *
     * @param visible The new visibility flag
     * @return This widget
     */
    Widget setVisible(boolean visible);

    /**
     * @return The position of this widget
     */
    WidgetPos getPos();

    /**
     * @return The horizontal alignment of this widdget
     */
    WidgetAlignment getAlignment();

    /**
     * @return The width of the widget
     */
    float getWidth();

    /**
     * @return The height of the widget
     */
    float getHeight();

    /**
     * @return The visibility flag of the widget
     */
    boolean isVisible();
}
