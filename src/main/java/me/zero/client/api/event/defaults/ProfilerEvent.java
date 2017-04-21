package me.zero.client.api.event.defaults;

/**
 * Called when a section is started in the profiler
 *
 * @since 1.0
 *
 * Created by Brady on 4/8/2017.
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
