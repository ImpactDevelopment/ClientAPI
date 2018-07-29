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
 * Generic input event. Cancelling will cause any ClientAPI
 * listeners to the event to not proceed with processing the event.
 *
 * @see KeyEvent
 * @see ClickEvent
 *
 * @author Brady
 * @since 11/3/2017 11:06 AM
 */
abstract class RawInputEvent extends InputEvent {

    /**
     * The physical key that was pressed or clicked
     */
    private final int key;

    /**
     * The action that occurred with the key
     *
     * @see GLFW#GLFW_RELEASE
     * @see GLFW#GLFW_PRESS
     * @see GLFW#GLFW_REPEAT
     */
    private final int action;

    RawInputEvent(int key, int action, int mods) {
        super(mods);
        this.key = key;
        this.action = action;
    }

    /**
     * @return The key that was pressed or clicked
     */
    public final int getKey() {
        return this.key;
    }

    /**
     * @return The action that occurred with the key
     */
    public final int getAction() {
        return this.action;
    }
}
