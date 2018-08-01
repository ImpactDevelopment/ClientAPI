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

package clientapi.event.defaults.game.core;

import org.lwjgl.glfw.GLFW;

/**
 * Event called when a Mouse button is pressed outside of a gui screen while in-game.
 *
 * @see KeyEvent
 *
 * @see GLFW#GLFW_MOUSE_BUTTON_LEFT
 * @see GLFW#GLFW_MOUSE_BUTTON_RIGHT
 * @see GLFW#GLFW_MOUSE_BUTTON_MIDDLE
 *
 * @author Brady
 * @since 1/20/2017 12:00 PM
 */
public final class ClickEvent extends RawInputEvent {

    public ClickEvent(int key, int action, int modifiers) {
        super(key, action, modifiers);
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "key=" + key +
                ", action=" + action +
                ", modifiers=" + Integer.toHexString(modifiers) +
                '}';
    }
}
