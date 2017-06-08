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

package me.zero.client.api.gui.tab.elements;

import me.zero.client.api.gui.tab.ITabGuiElement;

/**
 * A simple tab gui element that is just text
 *
 * @author Brady
 * @since 2/6/2017 12:00 PM
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
    public final String getText() {
        return text;
    }

    /**
     * Sets the text of this Text Element
     *
     * @param text The text that will be displayed
     */
    public final void setText(String text) {
        this.text = text;
    }

    @Override
    public void keyPress(int key) {}

    @Override
    public final boolean isActive() {
        return false;
    }

    @Override
    public void toggle() {}
}
