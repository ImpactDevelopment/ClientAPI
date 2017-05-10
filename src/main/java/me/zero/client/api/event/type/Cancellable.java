package me.zero.client.api.event.type;

/**
 * Type of Event that can be cancelled
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/10/2017 12:00PM
 */
public class Cancellable {

    /**
     * Cancelled state
     */
    private boolean cancelled;

    /**
     * Cancels the event, this is handled
     * wherever the event is injected to
     * prevent a task from occuring
     *
     * @since 1.0
     */
    public final void cancel() {
        this.cancelled = true;
    }

    /**
     * @since 1.0
     *
     * @return Whether or not the event is cancelled
     */
    public final boolean isCancelled() {
        return this.cancelled;
    }
}
