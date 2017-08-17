/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.event.defaults.game.render;

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
