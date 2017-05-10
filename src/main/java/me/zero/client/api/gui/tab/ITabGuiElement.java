package me.zero.client.api.gui.tab;

import me.zero.client.api.gui.IRenderer;

/**
 * Base for Tab Gui Elements
 *
 * @see me.zero.client.api.gui.tab.ITabGui
 *
 * @author Brady
 * @since 1/20/2017 12:00 PM
 */
public interface ITabGuiElement extends IRenderer {

    /**
     * @see #getTextColor()
     *
     * @return The text that will be displayed by the renderer
     */
    String getText();

    /**
     * @see #getText()
     *
     * @return The color of the Text that is rendered
     */
    default int getTextColor() {
        return 0xFFFFFFFF;
    }

    /**
     * Called whenever a key is pressed
     *
     * @param key - The key that iis pressed
     */
    void keyPress(int key);

    /**
     * @see #toggle()
     *
     * @return The state of the Element
     */
    boolean isActive();

    /**
     * Toggles the Active state
     *
     * @see #isActive()
     */
    void toggle();
}
