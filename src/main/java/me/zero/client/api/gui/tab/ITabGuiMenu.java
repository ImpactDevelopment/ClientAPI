package me.zero.client.api.gui.tab;

import java.util.List;

/**
 * Base for Tab Gui Menus.
 * A Menu is an Element that has sub-elements.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/6/2017 12:00PM
 */
public interface ITabGuiMenu extends ITabGuiElement {

    /**
     * @since 1.0
     *
     * @return The list of sub-elements that belong to this menu
     */
    List<ITabGuiElement> getElements();

    /**
     * @since 1.0
     *
     * @return The current selected element
     */
    ITabGuiElement getSelectedElement();

    /**
     * @since 1.0
     *
     * @return The current selected index
     */
    int getSelected();
}
