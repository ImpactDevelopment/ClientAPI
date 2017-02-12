package me.zero.client.api.event;

/**
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
