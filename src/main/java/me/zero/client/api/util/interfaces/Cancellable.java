package me.zero.client.api.util.interfaces;

/**
 * Interface that should be implemented in events that should be cancellable
 *
 * @since 1.0
 *
 * Created by Brady on 2/7/2017.
 */
public interface Cancellable {

    /**
     * @since 1.0
     *
     * @return Whether or not the event has been cancelled yet
     */
    boolean isCancelled();

    /**
     * Sets the cancelled state
     *
     * @since 1.0
     *
     * @param cancelled The new state
     */
    void setCancelled(boolean cancelled);
}
