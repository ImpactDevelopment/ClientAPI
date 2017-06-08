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

package me.zero.client.api.event.bench;

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
     * Current number of benchmark threads running
     */
    private static int threads;

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
            threads--;
        }, String.format("Benchmark #%s", ++threads)).start();
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
