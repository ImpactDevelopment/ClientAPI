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

package clientapi.util.server;

import clientapi.ClientAPI;
import clientapi.event.defaults.filters.PacketFilter;
import clientapi.event.defaults.game.core.TickEvent;
import clientapi.event.defaults.game.network.PacketEvent;
import clientapi.util.Timer;
import clientapi.util.interfaces.Helper;
import com.google.common.collect.Sets;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.server.SPacketTabComplete;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Used to find possible plugins on servers
 *
 * @author Brady
 * @since 3/24/2017
 */
public final class PluginFinder implements Helper {

    /**
     * Called in response to finding plugins
     */
    private Consumer<PluginFinderResponse> callback;

    /**
     * Used to keep track of the timeout
     */
    private Timer packetTimer = new Timer();

    /**
     * Timeout in MS, how long the Plugin Finder will listen
     */
    private long timeout;

    /**
     * Finds the plugin list, the callback is called
     * when the request times out or the list has been
     * found.
     *
     * @param callback Plugin Response callback
     */
    public final void find(Consumer<PluginFinderResponse> callback) {
        this.find(callback, 10000);
    }

    /**
     * Finds the plugin list, the callback is called
     * when the request times out or the list has been
     * found.
     *
     * @param callback Plugin Response callback
     * @param timeout Timeout in milliseconds
     */
    public final void find(Consumer<PluginFinderResponse> callback, long timeout) {
        packetTimer.reset();
        this.timeout = timeout;

        ClientAPI.EVENT_BUS.subscribe(this);
        this.callback = callback;
        mc.player.connection.sendPacket(new CPacketTabComplete("/", null, false));
    }

    @EventHandler
    private final Listener<TickEvent> tickListener = new Listener<>(event -> {
        ClientAPI.EVENT_BUS.unsubscribe(this);
        callback.accept(new PluginFinderResponse("Request timed out after " + timeout + "ms"));
    }, e -> packetTimer.delay(timeout));

    @EventHandler
    private final Listener<PacketEvent.Receive> packetListener = new Listener<>(event -> {
        SPacketTabComplete packet = (SPacketTabComplete) event.getPacket();
        Set<String> plugins = Sets.newLinkedHashSet();

        Arrays.stream(packet.getMatches()).filter(s -> s.contains(":")).forEach(s -> {
            String plugin = s.split(":")[0].substring(1);
            if (!plugins.contains(plugin) && !plugin.equalsIgnoreCase("minecraft") && !plugin.equalsIgnoreCase("bukkit"))
                plugins.add(plugin);
        });

        callback.accept(new PluginFinderResponse(plugins));
        callback = null;
        ClientAPI.EVENT_BUS.unsubscribe(this);
    }, new PacketFilter<>(SPacketTabComplete.class));
}
