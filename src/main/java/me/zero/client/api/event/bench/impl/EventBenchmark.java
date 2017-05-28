package me.zero.client.api.event.bench.impl;

import me.zero.client.api.ClientAPI;
import me.zero.event.listener.EventHandler;
import me.zero.event.listener.Listener;
import me.zero.client.api.event.bench.Benchmark;

/**
 * Implementation of Benchmark that invokes
 * events to test how fast the event system is
 *
 * @author Brady
 * @since 5/17/2017 2:34 PM
 */
public final class EventBenchmark extends Benchmark {

    public EventBenchmark(int passes, int invokations) {
        super(passes, invokations);
    }

    @Override
    protected void pre() {
        ClientAPI.EVENT_BUS.subscribe(this);
    }

    @Override
    protected void run() {
        ClientAPI.EVENT_BUS.post(new Object());
    }

    @Override
    protected void post() {
        ClientAPI.EVENT_BUS.unsubscribe(this);
    }

    @EventHandler
    private final Listener<Object> benchListener = new Listener<>(event -> { });
}
