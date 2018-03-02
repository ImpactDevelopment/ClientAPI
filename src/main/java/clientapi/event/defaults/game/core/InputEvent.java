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

import me.zero.alpine.type.Cancellable;

/**
 * Generic input event. Cancelling will cause any ClientAPI
 * listeners to the event to not proceed with processing the event.
 *
 * The keycode will be equal to its respective value
 * in {@code Keyboard} if the input is from the keyboard.
 *
 * The keycode will be equal to {@code button - 100}
 * if the input is from the mouse.
 *
 * @see KeyEvent
 * @see ClickEvent
 *
 * @author Brady
 * @since 11/3/2017 11:06 AM
 */
abstract class InputEvent extends Cancellable {

    /**
     * The key that was pressed or clicked
     */
    protected final int key;

    InputEvent(int key) {
        this.key = key;
    }

    /**
     * @return The key that was pressed or clicked
     */
    public final int getKey() {
        return this.key;
    }
}
