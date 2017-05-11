package me.zero.client.api.event.bench;

/**
 * Posted to the event manager when benchmarking the event system
 *
 * @author Brady
 * @since 5/11/2017 5:23 PM
 */
final class BenchEvent {

    private final String value;

    BenchEvent(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}
