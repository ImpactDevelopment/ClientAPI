package me.zero.client.api.event.bench;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.util.Callback;

/**
 * Used to benchmark the event system, serves
 * the purpose for testing the event system to
 * improve performance.
 *
 * @author Brady
 * @since 5/11/2017 6:11 PM
 */
public final class Benchmark {

    /**
     * Number of times that "passes" are made where the defined
     * amount of invokations occur.
     */
    private final int passes;

    /**
     * Number of times that the {@code BenchEvent} is invoked per pass
     */
    private final int invokations;

    public Benchmark(int passes, int invokations) {
        this.passes = passes;
        this.invokations = invokations;
    }

    public final void run(Callback<BenchResult> callback) {
        new Thread(() -> {
            BenchListener listener = new BenchListener();
            EventManager.subscribe(listener);

            long[] results = new long[passes];
            for (int pass = 0; pass < passes; pass++) {
                long time = System.nanoTime();
                for (int i = 0; i < invokations; i++)
                    EventManager.post(new BenchEvent("I like memes"));

                long diff = System.nanoTime() - time;
                results[pass] = diff;
            }

            EventManager.unsubscribe(listener);

            callback.call(new BenchResult(passes, invokations, results));
        }, "Event Benchmark").start();
    }
}
