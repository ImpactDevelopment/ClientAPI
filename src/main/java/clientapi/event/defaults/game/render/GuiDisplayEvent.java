/*
 * Copyright 2018 ImpactDevelopment
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

import me.zero.alpine.type.EventState;
import net.minecraft.client.gui.GuiScreen;

/**
 * Called when a Gui Screen is displayed. The screen can be
 * overriden by using the {@link GuiDisplayEvent#setScreen(GuiScreen)} method.
 *
 * @author Brady
 * @since 2/23/2017
 */
public final class GuiDisplayEvent {

    /**
     * The state of the event, whether or not it was invoked before or after the target.
     */
    private final EventState state;

    /**
     * The GuiScreen being displayed
     */
    private GuiScreen screen;

    public GuiDisplayEvent(EventState state, GuiScreen screen) {
        this.state = state;
        this.screen = screen;
    }

    /**
     * Sets the gui screen that will be displayed.
     * Replaces the screen that would've been displayed.
     *
     * @param screen The new gui screen that will be displayed
     * @return This event
     */
    public GuiDisplayEvent setScreen(GuiScreen screen) {
        this.screen = screen;
        return this;
    }

    /**
     * @return The state of this event
     */
    public final EventState getState() {
        return this.state;
    }

    /**
     * @return The screen being displayed
     */
    public final GuiScreen getScreen() {
        return this.screen;
    }

    @Override
    public String toString() {
        return "GuiDisplayEvent{" +
                "state=" + state +
                ", screen=" + screen +
                '}';
    }
}
