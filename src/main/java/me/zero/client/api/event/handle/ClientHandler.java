package me.zero.client.api.event.handle;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.defaults.*;
import me.zero.client.api.event.defaults.filters.PacketFilter;
import me.zero.client.api.event.type.EventPriority;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.keybind.Keybind;
import me.zero.client.api.util.render.camera.Camera;
import me.zero.client.api.util.render.camera.CameraManager;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketChat;
import org.lwjgl.input.Keyboard;

import java.util.stream.Stream;

import static org.lwjgl.input.Keyboard.KEYBOARD_SIZE;

/**
 * Some basic events that the client uses
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/9/2017 12:00PM
 */
public final class ClientHandler implements Helper {

    /**
     * A map of all key states
     */
    private final boolean[] keyMap = new boolean[KEYBOARD_SIZE];

    /**
     * Handles camera updates
     */
    @EventHandler
    private final Listener<RenderHudEvent> render2DListener = new Listener<>(event ->
        CameraManager.getInstance().getData().stream().filter(Camera::isVisible).forEach(camera -> camera.updateFramebuffer(event.getPartialTicks())),
            EventPriority.LOWEST);

    /**
     * Handles keybinds
     */
    @EventHandler
    private final Listener<KeyEvent> keyListener = new Listener<>(event ->
        Keybind.getKeybinds().stream().filter(keybind -> keybind.getKey() == event.getKey()).forEach(Keybind::onClick));

    /**
     *
     */
    @EventHandler
    private final Listener<ProfilerEvent> profilerListener = new Listener<>(event -> {
        String section = event.getSection();

        if (section != null && section.equalsIgnoreCase("hand") && !Camera.isCapturing())
            EventManager.post(new Render3DEvent());
    });

    /**
     * Handles key states
     */
    @EventHandler
    private final Listener<TickEvent> tickListener = new Listener<>(event -> {
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
    });

    /**
     * Handles chat messages
     */
    @EventHandler
    private final Listener<PacketEvent> packetListener = new Listener<>(event -> {
        if (event.getPacket() instanceof CPacketChatMessage) {
            CPacketChatMessage packet = (CPacketChatMessage) event.getPacket();
            ChatEvent chatEvent = new ChatEvent(packet.getMessage(), ChatEvent.Type.SEND);
            EventManager.post(chatEvent);
            if (chatEvent.isCancelled())
                event.cancel();
        } else if (event.getPacket() instanceof SPacketChat) {
            SPacketChat packet = (SPacketChat) event.getPacket();
            EventManager.post(new ChatEvent(packet.getChatComponent(), ChatEvent.Type.RECEIVE));
        }
    }, new PacketFilter(CPacketChatMessage.class, SPacketChat.class));
}
