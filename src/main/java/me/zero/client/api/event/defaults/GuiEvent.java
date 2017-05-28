package me.zero.client.api.event.defaults;

import net.minecraft.client.gui.GuiScreen;

/**
 * Called when a Gui Screen is displayed. The
 * screen can be overriden by using the
 * {@code setScreen(GuiScreen)} method, which
 * will completely override the screen that would've
 * been displayed.
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
     * @param screen The new gui screen that will be displayed
     * @return This event
     */
    public GuiEvent setScreen(GuiScreen screen) {
        this.screen = screen;
        return this;
    }

    /**
     * @return The screen being displayed
     */
    public final GuiScreen getScreen() {
        return this.screen;
    }
}
