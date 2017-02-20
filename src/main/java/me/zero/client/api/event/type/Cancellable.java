package me.zero.client.api.event.type;

/**
 * Type of Event that can be cancelled
 *
 * @since 1.0
 *
 * Created by Brady on 2/10/2017.
 */
public class Cancellable {

    /**
     * Cancelled state
     */
    private boolean cancelled;

    /**
     * Sets the cancelled state
     *
     * @since 1.0
     *
     * @param cancelled The new cancelled state
     */
    public final void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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
