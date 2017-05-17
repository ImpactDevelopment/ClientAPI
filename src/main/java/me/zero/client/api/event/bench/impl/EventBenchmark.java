package me.zero.client.api.event.bench.impl;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.bench.Benchmark;

/**
 * Implementation of Benchmark that invokes
 * events to test how fast the event system is
 *
 * @author Brady
 * @since 5/17/2017 2:34 PM
 */
public class EventBenchmark extends Benchmark {

    public EventBenchmark(int passes, int invokations) {
        super(passes, invokations);
    }

    @Override
    protected void pre() {
        EventManager.subscribe(this);
    }

    @Override
    protected void run() {
        EventManager.post(new Object());
    }

    @Override
    protected void post() {
        EventManager.unsubscribe(this);
    }

    @EventHandler
    private final Listener<Object> benchListener = new Listener<>(event -> { });
}
