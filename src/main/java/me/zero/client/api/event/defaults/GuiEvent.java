package me.zero.client.api.event.defaults;

import net.minecraft.client.gui.GuiScreen;

/**
 * Called when a Gui Screen is displayed
 *
 * @since 1.0
 *
 * Created by Brady on 2/23/2017.
 */
public class GuiEvent {

    private GuiScreen screen;

    public GuiEvent(GuiScreen screen) {
        this.screen = screen;
    }

    public GuiEvent setScreen(GuiScreen screen) {
        this.screen = screen;
        return this;
    }

    public GuiScreen getScreen() {
        return this.screen;
    }
}
