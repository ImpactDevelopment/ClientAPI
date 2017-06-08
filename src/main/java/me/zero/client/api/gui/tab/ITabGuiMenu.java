/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.gui.tab;

import java.util.List;

/**
 * Base for Tab Gui Menus.
 * A Menu is an Element that has sub-elements.
 *
 * @author Brady
 * @since 2/6/2017 12:00 PM
 */
public interface ITabGuiMenu extends ITabGuiElement {

    /**
     * @return The list of sub-elements that belong to this menu
     */
    List<ITabGuiElement> getElements();

    /**
     * @return The current selected element
     */
    ITabGuiElement getSelectedElement();

    /**
     * @return The current selected index
     */
    int getSelected();
}
