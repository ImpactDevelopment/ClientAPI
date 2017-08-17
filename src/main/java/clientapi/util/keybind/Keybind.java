/*
 * Copyright 2017 ZeroMemes
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

package clientapi.util.keybind;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static clientapi.util.keybind.Keybind.Action.*;

/**
 * A keybind that is used to create key hooks
 *
 * @author Brady
 * @since 2/10/2017 12:00 PM
 */
public final class Keybind {

    /**
     * The List of all Keybind Objects
     */
    private static final List<Keybind> keybinds = new ArrayList<>();

    /**
     * The type of keybind
     */
    private Type type;

    /**
     * The KeyCode for this Keybind
     */
    private int key;

    /**
     * The consumer that handles various key events
     */
    private final Consumer<Action> consumer;

    public Keybind(Type type, int key, Consumer<Action> consumer) {
        this.type = type;
        this.key = key;
        this.consumer = consumer;
        Keybind.keybinds.add(this);
    }

    /**
     * @param key The key code being set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * @return The key code of this bind
     */
    public int getKey() {
        return this.key;
    }

    /**
     * @param type The new type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return The type of keybind
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Called when the key's state has been changed,
     * indicating that an action should take place
     */
    public final void onClick() {
        consumer.accept(CLICK);
    }

    /**
     * Called when a key is pressed
     */
    public final void onPress() {
        consumer.accept(PRESS);
        if (type == Type.HOLD)
            onClick();
    }

    /**
     * Called when a key is released
     */
    public final void onRelease() {
        consumer.accept(RELEASE);
        if (type == Type.HOLD)
            onClick();
    }

    /**
     * @return The list of all Keybind Objects
     */
    public static List<Keybind> getKeybinds() {
        return Keybind.keybinds;
    }

    /**
     * Keybind type
     */
    public enum Type {
        /**
         * Calls onClick whenever the defined keycode's
         * state is changed from false to true, indicating
         * that the key was pressed.
         */
        TOGGLE,

        /**
         * Call onClick whenever the defined keycode's
         * state is changed, indicating that the key was
         * being held/released, and that changed.
         */
        HOLD
    }

    /**
     * Type of Key Action
     */
    public enum Action {
        CLICK, PRESS, RELEASE
    }
}
