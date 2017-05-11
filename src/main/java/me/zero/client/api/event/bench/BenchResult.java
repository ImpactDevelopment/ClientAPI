package me.zero.client.api.event.bench;

/**
 * Holds data from a benchmark
 *
 * @author Brady
 * @since 5/11/2017 6:15 PM
 */
public final class BenchResult {

    /**
     * Number of passes made on the benchmark
     */
    private final int passes;

    /**
     * Number of invokations per pass
     */
    private final int invokations;

    /**
     * The time in nanoseconds for each pass
     */
    private final long[] results;

    public BenchResult(int passes, int invokations, long[] results) {
        this.passes = passes;
        this.invokations = invokations;
        this.results = results;
    }

    /**
     * @return The amount of passes
     */
    public final int getPasses() {
        return this.passes;
    }

    /**
     * @return The amount of invokations per pass
     */
    public final int getInvokations() {
        return this.invokations;
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
        long total = 0;
        for (long result : results)
            total += result;
        return total;
    }

    /**
     * @return The average time that each pass took
     */
    public final long getAverage() {
        return this.getTotal() / this.passes;
    }
}
