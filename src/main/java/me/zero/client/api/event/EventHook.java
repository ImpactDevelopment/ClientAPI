package me.zero.client.api.event;

/**
 * The body of a listener, called when an event is invoked
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/22/2017 12:00 PM
 */
public interface EventHook<T> {

    /**
     * Invokes this Listener with the event
     *
     * @param event The Event being Invoked
     */
    void invoke(T event);
}
