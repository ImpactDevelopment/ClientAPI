package me.zero.client.api.event;

import java.io.Serializable;

/**
 * Created by Brady on 1/22/2017.
 */
public interface EventHook<T> extends Serializable {

    /**
     * Invokes this Listener with the event
     *
     * @param event The Event being Invoked
     */
    void invoke(T event);
}
