package me.zero.client.api.event.defaults;

/**
 * Called when a section is started in the profiler
 *
 * @since 1.0
 *
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
public final class ProfilerEvent {

    /**
     * Current profiler section
     */
    private final String section;

    public ProfilerEvent(String section) {
        this.section = section;
    }

    /**
     * @since 1.0
     *
     * @return The current profiler section
     */
    public final String getSection() {
        return this.section;
    }
}
