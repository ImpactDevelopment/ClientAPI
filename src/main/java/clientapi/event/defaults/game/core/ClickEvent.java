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

import net.minecraft.client.Minecraft;

/**
 * Event called when a Mouse button is pressed
 * outside of a gui screen while in-game. Default
 * recognized buttons are LEFT, RIGHT and MIDDLE.
 *
 * @see KeyEvent
 * @see MouseButton
 * @see Minecraft#runTickMouse()
 *
 * @author Brady
 * @since 1/20/2017
 */
public final class ClickEvent extends InputEvent {

    public ClickEvent(int key) {
        super(key - 100);
    }

    /**
     * Returns the actual mouse button that was pressed.
     * Equal to {@code getKey() + 100}. Should be used
     * instead of getKey() in the context of comparing the
     * actual mouse button that was pressed.
     *
     * @return The mouse button pressed
     */
    public final int getButton() {
        return this.key + 100;
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "key=" + key +
                '}';
    }

    public interface MouseButton {
        int LEFT = 0;
        int RIGHT = 1;
        int MIDDLE = 2;
    }
}
