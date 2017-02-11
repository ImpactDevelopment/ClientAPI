package me.zero.client.api.gui;

import net.minecraft.client.gui.FontRenderer;

/**
 * Gives a class the ability to be rendered.
 * Rendering is not forced to be implemented.
 *
 * @since 1.0
 *
 * Created by Brady on 2/6/2017.
 */
public interface IRenderer {

    /**
     * Renders this object
     *
     * @since 1.0
     *
     * @param x The X position of where this render object will start
     * @param y The Y position of where this render object will start
     * @param font The FontRenderer being used in drawing
     */
    default void render(float x, float y, FontRenderer font) {}
}
