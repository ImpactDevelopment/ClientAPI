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
 * Called whenever a physical key associated
 * with a unicode character is pressed.
 *
 * @author Brady
 * @since 7/28/2018 8:23 PM
 */
public final class CharacterEvent extends InputEvent {

    /**
     * The character that was pressed
     */
    private final char character;

    public CharacterEvent(char character, int modifiers) {
        super(modifiers);
        this.character = character;
    }

    /**
     * @return The character that was pressed
     */
    public final char getCharacter() {
        return this.character;
    }
}
