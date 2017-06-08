/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.util.interfaces;

/**
 * Simple Toggleable interface
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
 */
public interface Toggleable {

    /**
     * Called when the state is changed from false to true
     */
    default void onEnable() {}

    /**
     * Called when the state is changed from true to false
     */
    default void onDisable() {}

    /**
     * Toggles the state
     */
    default void toggle() {
        this.setState(!this.getState());
    }

    /**
     * Directly sets the state
     *
     * @param state The new state
     */
    void setState(boolean state);

    /**
     * @return The current state
     */
    boolean getState();
}
