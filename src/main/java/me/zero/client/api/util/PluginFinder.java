package me.zero.client.api.util;

import com.google.common.collect.Sets;
import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.defaults.PacketEvent;
import me.zero.client.api.event.defaults.TickEvent;
import me.zero.client.api.event.defaults.filters.PacketFilter;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.server.SPacketTabComplete;
import net.minecraft.util.math.BlockPos;

import java.util.Arrays;
import java.util.Set;

import static me.zero.client.api.event.defaults.PacketEvent.Type.RECEIVE;
import static me.zero.client.api.util.PluginFinder.Result.FAILURE;
import static me.zero.client.api.util.PluginFinder.Result.SUCCESS;

/**
 * Created by Brady on 3/24/2017.
 */
public class PluginFinder implements Helper {

    /**
     * Called in response to finding plugins
     */
    private Callback<PResponse> callback;

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
     * @since 1.0
     *
     * @param callback Plugin Response callback
     */
    public final void find(Callback<PResponse> callback) {
        this.find(callback, 10000);
    }

    /**
     * Finds the plugin list, the callback is called
     * when the request times out or the list has been
     * found.
     *
     * @since 1.0
     *
     * @param callback Plugin Response callback
     * @param timeout Timeout in MS
     */
    public final void find(Callback<PResponse> callback, long timeout) {
        packetTimer.reset();
        this.timeout = timeout;

        EventManager.subscribe(this);
        this.callback = callback;
        mc.player.connection.sendPacket(new CPacketTabComplete("/", BlockPos.ORIGIN, false));
    }

    @EventHandler
    private Listener<TickEvent> tickListener = new Listener<>(event -> {
        if (!packetTimer.delay(timeout))
            return;

        EventManager.unsubscribe(this);
        callback.call(new PResponse("Request timed out after " + timeout + "ms"));
    });

    @EventHandler
    private Listener<PacketEvent> packetListener = new Listener<>(event -> {
        if (event.getType() != RECEIVE)
            return;

        SPacketTabComplete packet = (SPacketTabComplete) event.getPacket();
        Set<String> plugins = Sets.newLinkedHashSet();

        Arrays.stream(packet.getMatches()).filter(s -> s.contains(":")).forEach(s -> {
            String plugin = s.split(":")[0].substring(1);
            if (!plugins.contains(plugin) && !plugin.equalsIgnoreCase("minecraft") && !plugin.equalsIgnoreCase("bukkit"))
                plugins.add(plugin);
        });

        callback.call(new PResponse(plugins));
        callback = null;
        EventManager.unsubscribe(this);
    }, new PacketFilter(SPacketTabComplete.class));

    public static class PResponse {

        /**
         * The list of plugins found
         */
        private Set<String> plugins;

        /**
         * The last error
         */
        private String error;

        /**
         * The result status, either SUCCESS or FAILURE
         */
        private Result result;

        private PResponse(String error) {
            this.error = error;
            this.result = FAILURE;
        }

        private PResponse(Set<String> plugins) {
            this.plugins = plugins;
            this.result = SUCCESS;
        }

        /**
         * @since 1.0
         *
         * @return The plugins found, if found
         */
        public Set<String> getPlugins() {
            return this.plugins;
        }

        /**
         * @since 1.0
         *
         * @return The last error, if there is one
         */
        public String getError() {
            return this.error;
        }

        /**
         * @since 1.0
         *
         * @return The outcome of the request, SUCCESS or FAILURE
         */
        public Result getResult() {
            return this.result;
        }
    }

    public enum Result {
        SUCCESS, FAILURE
    }
}
