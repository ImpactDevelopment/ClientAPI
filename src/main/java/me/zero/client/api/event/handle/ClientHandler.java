package me.zero.client.api.event.handle;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.*;
import me.zero.client.api.exception.ActionNotValidException;
import me.zero.client.api.util.ClientUtils;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.keybind.Keybind;
import me.zero.client.api.util.render.camera.CameraManager;
import me.zero.client.load.ClientLoader;
import org.lwjgl.input.Keyboard;

import java.util.stream.Stream;

import static org.lwjgl.input.Keyboard.KEYBOARD_SIZE;

/**
 * Some basic events that the client uses
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
 */
public final class ClientHandler implements Helper {

    private boolean[] keyMap = new boolean[KEYBOARD_SIZE];

    public ClientHandler() {
        if (ClientUtils.traceSource() != ClientLoader.class)
            throw new ActionNotValidException("The ClientHandler can only be initialized from the ClientLoader");
    }

    @EventHandler
    private void onRender2D(Render2DEvent event) {
        CameraManager.getInstance().getData().forEach(camera -> camera.updateFramebuffer(event.getPartialTicks()));
    }

    @EventHandler
    private void onKey(KeyEvent event) {
        Keybind.getKeybinds().stream().filter(keybind -> keybind.getKey() == event.getKey()).forEach(Keybind::onClick);
    }

    @EventHandler
    private void onTick(TickEvent event) {
        if (mc.currentScreen != null) return;

        for (int i = 1; i < KEYBOARD_SIZE; i++) {
            final int key = i;
            boolean currentState = Keyboard.isKeyDown(i);
            if (currentState != keyMap[i]) {
                if (keyMap[i] = !keyMap[i])
                    EventManager.post(new KeyEvent(i));
                Stream<Keybind> keybinds = Keybind.getKeybinds().stream().filter(keybind -> keybind.getKey() == key);
                if (currentState)
                    keybinds.forEach(Keybind::onPress);
                else
                    keybinds.forEach(Keybind::onRelease);
            }
        }
    }
}
