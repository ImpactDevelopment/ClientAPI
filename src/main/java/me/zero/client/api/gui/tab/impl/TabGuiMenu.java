package me.zero.client.api.gui.tab.impl;

import me.zero.client.api.gui.tab.ITabGuiElement;
import me.zero.client.api.gui.tab.ITabGuiMenu;
import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.input.Keyboard.*;

/**
 * Implementationm of ITabGuiMenu
 *
 * @see me.zero.client.api.gui.tab.ITabGuiMenu
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/6/2017 12:00 PM
 */
public class TabGuiMenu implements ITabGuiMenu {

    /**
     * The list of sub-elements
     */
    protected List<ITabGuiElement> elements = new ArrayList<>();

    /**
     * The title of the menu
     */
    protected String text;

    /**
     * The state of whether or not the menu is open
     */
    protected boolean active;

    /**
     * Current selected index
     */
    protected int selected;

    /**
     * Last selected index
     */
    protected int lastSelected;

    public TabGuiMenu(String text) {
        this.text = text;
    }

    @Override
    public void render(float x, float y, FontRenderer font) {
        // To be overriden by superclass
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
        ITabGuiElement element = selectedElement();
        switch (key) {
            case KEY_UP : {
                if (!active) return;
                if (element == null) return;
                if (element.isActive()) {
                    element.keyPress(key);
                    return;
                }

                lastSelected = selected;
                selected--;
                if (selected < 0) {
                    selected = elements.size() - 1;
                }
                break;
            }
            case KEY_DOWN : {
                if (!active) return;
                if (element == null) return;
                if (element.isActive()) {
                    element.keyPress(key);
                    return;
                }

                lastSelected = selected;
                selected++;
                if (selected > elements.size() - 1) {
                    selected = 0;
                }
                break;
            }
            case KEY_RIGHT : {
                if (!active) {
                    this.toggle();
                    return;
                }
                if (element == null) {
                    this.interact();
                    return;
                }
                if (element.isActive()) {
                    element.keyPress(key);
                    return;
                }

                element.toggle();
                break;
            }
            case KEY_LEFT : {
                if (!active) { return; }
                if (element == null) {
                    this.toggle();
                    return;
                }
                if (element.isActive()) {
                    element.keyPress(key);
                    return;
                }

                this.toggle();
                break;
            }
            case KEY_RETURN : {
                if (!active) {
                    toggle();
                    return;
                }
                if (element == null) {
                    interact();
                    return;
                }
                if (element.isActive()) {
                    element.keyPress(key);
                    return;
                }
                element.toggle();
            }
        }
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

    public ITabGuiElement selectedElement() {
        if (selected < 0 || selected >= elements.size()) return null;
        return elements.get(selected);
    }
}
