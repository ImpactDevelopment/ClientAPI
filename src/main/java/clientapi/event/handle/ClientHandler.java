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

package clientapi.event.handle;

import clientapi.event.defaults.game.core.KeyEvent;
import clientapi.util.interfaces.Helper;
import clientapi.util.io.Keybind;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import org.lwjgl.glfw.GLFW;

import java.util.stream.Stream;

/**
 * Some basic events that the client uses
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public enum ClientHandler implements Helper {

    INSTANCE;

    /**
     * Handles keybinds
     */
    @EventHandler
    private final Listener<KeyEvent> keyListener = new Listener<>(event -> {
        System.out.println("KeyEvent {" + event.getKey() + " " + event.getAction() + " " + event.getModifiers() + " }");
        switch (event.getAction()) {
            case GLFW.GLFW_PRESS: {
                // Get all matching keybinds
                Stream<Keybind> keybinds = Keybind.getKeybinds().stream()
                        .filter(bind -> bind.getKey() != GLFW.GLFW_KEY_UNKNOWN && bind.getKey() == event.getKey());

                // Run onPress for all matching keybinds
                // and onClick for the toggle keybinds
                keybinds.forEach(keybind -> {
                    keybind.onPress();
                    if (keybind.getType() == Keybind.Type.TOGGLE)
                        keybind.onClick();
                });
                break;
            }
            case GLFW.GLFW_RELEASE: {
                Keybind.getKeybinds().stream()
                        .filter(bind -> bind.getKey() == event.getKey())
                        .forEach(Keybind::onRelease);
                break;
            }
        }
    });
}
