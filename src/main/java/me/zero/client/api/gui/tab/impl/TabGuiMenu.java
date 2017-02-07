package me.zero.client.api.gui.tab.impl;

import me.zero.client.api.gui.tab.ITabGuiElement;
import me.zero.client.api.gui.tab.ITabGuiMenu;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementationm of ITabGuiMenu
 *
 * @see me.zero.client.api.gui.tab.ITabGuiMenu
 *
 * @since 1.0
 *
 * Created by Brady on 2/6/2017.
 */
public class TabGuiMenu implements ITabGuiMenu {

    /**
     * The list of sub-elements
     */
    protected List<ITabGuiElement> elements = new ArrayList<>();

    /**
     * The title of the menu
     */
    private String text;

    /**
     * The state of whether or not the menu is open
     */
    private boolean active;

    /**
     * Current selected index
     */
    private int selected;

    public TabGuiMenu(String text) {
        this.text = text;
    }

    @Override
    public void render(float x, float y, FontRenderer font) {
        // Render
    }

    @Override
    public List<ITabGuiElement> getElements() {
        return elements;
    }

    @Override
    public ITabGuiElement getSelectedElement() {
        return elements.get(selected);
    }

    @Override
    public int getSelected() {
        return selected;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void keyPress(int key) {
        // handle key presses
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void toggle() {
        if (elements.size() == 0) {
            interact();
            return;
        }
        active = !active;
    }

    public void interact() {}
}
