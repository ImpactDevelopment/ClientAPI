package me.zero.client.api.event.bench;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.Listener;

/**
 * Instantiated and subscribed to the event manager
 * to listen to the benchmarks events that are posted
 *
 * @author Brady
 * @since 5/11/2017 6:12 PM
 */
final class BenchListener {

    @EventHandler
    @SuppressWarnings("unused")
    private final Listener<BenchEvent> benchListener = new Listener<>(event -> {
        // Invoked when the BenchEvent is called
    });
}
