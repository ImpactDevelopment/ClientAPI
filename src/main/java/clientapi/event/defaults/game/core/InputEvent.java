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
 * Basis for an input event, containing a single modifier
 * field that describes the modifiers that were held down
 * when the input event was called.
 *
 * @see GLFW#GLFW_MOD_SHIFT
 *
 * @author Brady
 * @since 7/28/2018 8:30 PM
 */
abstract class InputEvent {

    /**
     * The modifier keys that were held down at the time of the input
     */
    private final int modifiers;

    public InputEvent(int modifiers) {
        this.modifiers = modifiers;
    }

    /**
     * @return The modifier keys that were held down at the time of the input
     */
    public final int getModifiers() {
        return this.modifiers;
    }
}
