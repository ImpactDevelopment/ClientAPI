/*
 * Copyright 2017 ImpactDevelopment
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

import clientapi.ClientAPI;
import clientapi.event.defaults.filters.PacketFilter;
import clientapi.event.defaults.game.core.KeyEvent;
import clientapi.event.defaults.game.core.KeyUpEvent;
import clientapi.event.defaults.game.core.ProfilerEvent;
import clientapi.event.defaults.game.misc.ChatEvent;
import clientapi.event.defaults.game.network.PacketEvent;
import clientapi.event.defaults.game.render.RenderHudEvent;
import clientapi.event.defaults.game.render.RenderWorldEvent;
import clientapi.util.interfaces.Helper;
import clientapi.util.io.Keybind;
import clientapi.util.render.camera.Camera;
import clientapi.util.render.camera.CameraManager;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.type.EventPriority;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketChat;

import java.util.stream.Stream;

import static org.lwjgl.input.Keyboard.KEY_NONE;

/**
 * Some basic events that the client uses
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public enum ClientHandler implements Helper {

    INSTANCE;

    /**
     * Handles camera updates
     */
    @EventHandler
    private final Listener<RenderHudEvent> render2DListener = new Listener<>(event ->
            CameraManager.getInstance().forEach(camera -> camera.updateFramebuffer(event.getPartialTicks())), EventPriority.LOWEST);

    /**
     * Handles keybinds
     */
    @EventHandler
    private final Listener<KeyEvent> keyListener = new Listener<>(event -> {
        // If the event is cancelled, don't process the KeyEvent
        if (event.isCancelled())
            return;

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
    }, EventPriority.LOWEST);

    @EventHandler
    private final Listener<KeyUpEvent> keyUpListener = new Listener<>(event ->
            Keybind.getKeybinds().stream()
                    .filter(bind -> bind.getKey() == event.getKey())
                    .forEach(Keybind::onRelease));

    /**
     * Handles profiling events
     */
    @EventHandler
    private final Listener<ProfilerEvent> profilerListener = new Listener<>(event -> {
        String section = event.getSection();

        if (section != null && section.equalsIgnoreCase("hand") && !Camera.isCapturing())
            ClientAPI.EVENT_BUS.post(new RenderWorldEvent());
    });

    /**
     * Handles packet out-flow
     */
    @EventHandler
    private final Listener<PacketEvent.Send> packetSendListener = new Listener<>(event -> {
        CPacketChatMessage packet = (CPacketChatMessage) event.getPacket();
        ChatEvent chatEvent = new ChatEvent.Send(packet.getMessage());
        ClientAPI.EVENT_BUS.post(chatEvent);
        if (chatEvent.isCancelled())
            event.cancel();

        event.setPacket(new CPacketChatMessage(chatEvent.getRawMessage()));
    }, new PacketFilter<>(CPacketChatMessage.class));

    /**
     * Handles packet in-flow
     */
    @EventHandler
    private final Listener<PacketEvent.Receive> packetReceiveListener = new Listener<>(event -> {
        SPacketChat packet = (SPacketChat) event.getPacket();
        ChatEvent chatEvent = new ChatEvent.Receive(packet.getChatComponent());
        ClientAPI.EVENT_BUS.post(chatEvent);
        if (chatEvent.isCancelled())
            event.cancel();
    }, new PacketFilter<>(SPacketChat.class));
}
