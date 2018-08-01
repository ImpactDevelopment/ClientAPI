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

/**
 * Event called when a Key is pressed outside of a GUI while in-game.
 * When cancelled, any ClientAPI related KeyEvent Listener won't process
 * the key press.
 *
 * @see ClickEvent
 *
 * @author Brady
 * @since 1/20/2017 12:00 PM
 */
public final class KeyEvent extends RawInputEvent {

    public KeyEvent(int key, int action, int modifiers) {
        super(key, action, modifiers);
    }

    @Override
    public String toString() {
        return "KeyEvent{" +
                "character=" + character +
                ", key=" + key +
                '}';
    }
}
