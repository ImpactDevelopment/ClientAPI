package me.zero.client.api.event.bench;

import me.zero.client.api.event.EventManager;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Used to benchmark the execution of code,
 * can be used for anything
 *
 * @author Brady
 * @since 5/11/2017 6:11 PM
 */
public abstract class Benchmark {

    /**
     * Number of times that "passes" are made where the defined
     * amount of invokations occur.
     */
    private final int passes;

    /**
     * Number of times that the {@code run} is executed per pass
     */
    private final int invokations;

    public Benchmark(int passes, int invokations) {
        this.passes = passes;
        this.invokations = invokations;
    }

    /**
     * Runs the benchmark, BenchResult is passed
     * to the specified consumer
     *
     * @param callback Consumer that the BenchResult is passed to
     */
    public final void run(Consumer<BenchResult> callback) {
        Objects.requireNonNull(callback);

        new Thread(() -> {
            pre();

            long[] results = new long[passes];
            for (int pass = 0; pass < passes; pass++) {
                long time = System.nanoTime();
                for (int i = 0; i < invokations; i++)
                    run();

                long diff = System.nanoTime() - time;
                results[pass] = diff;
            }

            post();

            callback.accept(new BenchResult(passes, invokations, results));
        }, "Benchmark").start();
    }

    /**
     * Called before all passes are made
     */
    protected void pre() {}

    /**
     * Called for each invokation pass
     */
    protected abstract void run();

    /**
     * Called after all passes are made
     */
    protected void post() {}
}
