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
import clientapi.event.defaults.game.core.KeyUpEvent;
import clientapi.util.interfaces.MinecraftAccessible;
import clientapi.util.io.Keybind;
import me.zero.alpine.event.EventPriority;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listenable;
import me.zero.alpine.listener.Listener;

import java.util.stream.Stream;

import static org.lwjgl.input.Keyboard.KEY_NONE;

/**
 * Some basic events that the client uses
 *
 * @author Brady
 * @since 2/9/2017
 */
public enum ClientHandler implements Listenable, MinecraftAccessible {

    INSTANCE;

    /**
     * Handles keybinds
     */
    @EventHandler
    private final Listener<KeyEvent> keyListener = new Listener<>(event -> {
        // Get all matching keybinds
        Stream<Keybind> keybinds = Keybind.getKeybinds().stream()
                .filter(bind -> bind.getKey() != KEY_NONE && bind.getKey() == event.getKey());

        // Run onPress for all matching keybinds
        // and onClick for the toggle keybinds
        keybinds.forEach(keybind -> {
            keybind.onPress();
            if (keybind.getType() == Keybind.Type.TOGGLE)
                keybind.onClick();
        });
    }, EventPriority.LOWEST, e -> !e.isCancelled());

    @EventHandler
    private final Listener<KeyUpEvent> keyUpListener = new Listener<>(event ->
            Keybind.getKeybinds().stream()
                    .filter(bind -> bind.getKey() == event.getKey())
                    .forEach(Keybind::onRelease));
}
