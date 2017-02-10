package me.zero.client.api.event.type;

/**
 * Type of Event that can be cancelled
 *
 * @since 1.0
 *
 * Created by Brady on 2/10/2017.
 */
public class Cancellable {

    private boolean cancelled;

    public final void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public final boolean isCancelled() {
        return this.cancelled;
    }
}
