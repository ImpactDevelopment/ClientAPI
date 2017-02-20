package me.zero.client.api.event;

/**
 * The body of a listener, called when an event is invoked
 *
 * @since 1.0
 *
 * Created by Brady on 1/22/2017.
 */
public interface EventHook<T> {

    /**
     * Invokes this Listener with the event
     *
     * @param event The Event being Invoked
     */
    void invoke(T event);
}
