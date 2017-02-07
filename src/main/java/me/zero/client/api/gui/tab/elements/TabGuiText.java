package me.zero.client.api.gui.tab.elements;

import me.zero.client.api.gui.tab.ITabGuiElement;

/**
 * A simple tab gui element that is just text
 *
 * @since 1.0
 *
 * Created by Brady on 2/6/2017.
 */
public class TabGuiText implements ITabGuiElement {

    /**
     * The text of this element
     */
    private String text;

    public TabGuiText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    /**
     * Sets the text of this Text Element
     *
     * @since 1.0
     *
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void keyPress(int key) {}

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void toggle() {}
}
