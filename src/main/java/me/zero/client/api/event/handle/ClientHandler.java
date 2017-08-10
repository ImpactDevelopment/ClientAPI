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

package me.zero.client.api.event.handle;

import me.zero.client.api.ClientAPI;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.client.api.event.defaults.filters.PacketFilter;
import me.zero.alpine.type.EventPriority;
import me.zero.client.api.event.defaults.game.core.*;
import me.zero.client.api.event.defaults.game.misc.ChatEvent;
import me.zero.client.api.event.defaults.game.network.PacketEvent;
import me.zero.client.api.event.defaults.game.render.Render3DEvent;
import me.zero.client.api.event.defaults.game.render.RenderHudEvent;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.keybind.Keybind;
import me.zero.client.api.util.render.camera.Camera;
import me.zero.client.api.util.render.camera.CameraManager;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.server.SPacketChat;

import java.util.stream.Stream;

/**
 * Some basic events that the client uses
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public final class ClientHandler implements Helper {

    /**
     * Handles camera updates
     */
    @EventHandler
    private final Listener<RenderHudEvent> render2DListener = new Listener<>(event ->
            CameraManager.getInstance().getData().forEach(camera -> camera.updateFramebuffer(event.getPartialTicks())), EventPriority.LOWEST);

    /**
     * Handles keybinds
     */
    @EventHandler
    private final Listener<KeyEvent> keyListener = new Listener<>(event -> {
        // Get all matching keybinds
        Stream<Keybind> keybinds = Keybind.getKeybinds().stream()
                .filter(bind -> bind.getKey() == event.getKey());

        // Run onClick for the toggle keybinds
        keybinds.filter(bind -> bind.getType() == Keybind.Type.TOGGLE)
                .forEach(Keybind::onClick);

        // Run onPres for all matching keybinds
        keybinds.forEach(Keybind::onPress);
    });

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
            ClientAPI.EVENT_BUS.post(new Render3DEvent());
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
