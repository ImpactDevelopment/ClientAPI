package me.zero.client.api.gui.widget.impl;

import me.zero.client.api.gui.widget.Widget;
import me.zero.client.api.gui.widget.data.WidgetAlignment;
import me.zero.client.api.gui.widget.data.WidgetPos;

/**
 * Implementation of Widget
 *
 * @see Widget
 *
 * @author Brady
 * @since 5/28/2017 10:00 AM
 */
public abstract class AbstractWidget implements Widget {

    /**
     * Position of this widget
     */
    private WidgetPos pos;

    /**
     * Alignment of this widget
     */
    private WidgetAlignment alignment;

    /**
     * The width of this widget. This should be
     * set after the widget is finished rendering.
     */
    private float width;

    /**
     * The height of this widget. This should be
     * set after the widget is finished rendering.
     */
    private float height;

    /**
     * The visibility of this widget
     */
    private boolean visible = true;

    @Override
    public Widget setPos(WidgetPos pos) {
        this.pos = pos;
        return this;
    }

    @Override
    public Widget setAlignment(WidgetAlignment alignment) {
        this.alignment = alignment;
        return this;
    }

    @Override
    public Widget setWidth(float width) {
        this.width = width;
        return this;
    }

    @Override
    public Widget setHeight(float height) {
        this.height = height;
        return this;
    }

    @Override
    public Widget setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    @Override
    public WidgetPos getPos() {
        return this.pos;
    }

    @Override
    public WidgetAlignment getAlignment() {
        return this.alignment;
    }

    @Override
    public float getWidth() {
        return this.width;
    }

    @Override
    public float getHeight() {
        return this.height;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }
}
