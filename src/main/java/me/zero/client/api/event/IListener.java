package me.zero.client.api.event;

/**
 * Created by Brady on 1/22/2017.
 */
interface IListener {

    /**
     * @since 1.0
     *
     * @return Event that is being targeted by this Listener
     */
    Class<?> getTarget();

    /**
     * @see me.zero.client.api.event.EventPriority
     *
     * @since 1.0
     *
     * @return The Call-Priority of this event
     */
    byte getPriority();

    /**
     * Invokes this Listener with the event
     *
     * @param event The Event being Invoked
     */
    void invoke(Object event);
}
