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

package clientapi.event.bench;

import java.util.stream.LongStream;

/**
 * Holds data from a benchmark
 *
 * @author Brady
 * @since 5/11/2017
 */
public final class BenchResult {

    /**
     * Number of passes made on the benchmark
     */
    private final int passes;

    /**
     * Number of invocations per pass
     */
    private final int invocations;

    /**
     * The time in nanoseconds for each pass
     */
    private final long[] results;

    BenchResult(int passes, int invocations, long[] results) {
        this.passes = passes;
        this.invocations = invocations;
        this.results = results;
    }

    /**
     * @return The amount of passes
     */
    public final int getPasses() {
        return this.passes;
    }

    /**
     * @return The amount of invocations per pass
     */
    public final int getInvocations() {
        return this.invocations;
    }

    /**
     * @return An array containing the time that each pass took in nanoseconds
     */
    public final long[] getResults() {
        return results;
    }

    /**
     * @return The total amount of nanoseconds that all passes took
     */
    public final long getTotal() {
        return LongStream.of(results).sum();
    }

    /**
     * @return The average time that each pass took
     */
    public final long getAverage() {
        return this.getTotal() / this.passes;
    }
}
