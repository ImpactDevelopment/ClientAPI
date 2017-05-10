package me.zero.client.api.event.defaults;

import net.minecraft.client.gui.GuiScreen;

/**
 * Called when a Gui Screen is displayed.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/23/2017 12:00 PM
 */
public final class GuiEvent {

    /**
     * The GuiScreen being displayed
     */
    private GuiScreen screen;

    public GuiEvent(GuiScreen screen) {
        this.screen = screen;
    }

    /**
     * Sets the gui screen that will be displayed.
     * Replaces the screen that would've been displayed.
     *
     * @since 1.0
     *
     * @param screen The new gui screen that will be displayed
     * @return This event
     */
    public GuiEvent setScreen(GuiScreen screen) {
        this.screen = screen;
        return this;
    }

    /**
     * @since 1.0
     *
     * @return The screen being displayed
     */
    public final GuiScreen getScreen() {
        return this.screen;
    }
}
