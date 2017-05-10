package me.zero.client.api.gui.tab;

import me.zero.client.api.gui.IRenderer;

/**
 * Base for Tab Gui Elements
 *
 * @see me.zero.client.api.gui.tab.ITabGui
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/20/2017 12:00PM
 */
public interface ITabGuiElement extends IRenderer {

    /**
     * @see #getTextColor()
     *
     * @since 1.0
     *
     * @return The text that will be displayed by the renderer
     */
    String getText();

    /**
     * @see #getText()
     *
     * @since 1.0
     *
     * @return The color of the Text that is rendered
     */
    default int getTextColor() {
        return 0xFFFFFFFF;
    }

    /**
     * Called whenever a key is pressed
     *
     * @since 1.0
     *
     * @param key - The key that iis pressed
     */
    void keyPress(int key);

    /**
     * @since 1.0
     *
     * @see #toggle()
     *
     * @return The state of the Element
     */
    boolean isActive();

    /**
     * Toggles the Active state
     *
     * @see #isActive()
     *
     * @since 1.0
     */
    void toggle();
}
