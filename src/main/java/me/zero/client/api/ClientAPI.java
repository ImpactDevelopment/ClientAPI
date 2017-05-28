package me.zero.client.api;

import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;

/**
 * Contains some constants that are used throughout the API.
 *
 * @author Brady
 * @since 5/27/2017 9:52 AM
 */
public final class ClientAPI {

    /**
     * Prevent instantiation
     */
    private ClientAPI() {}

    /**
     * Instance of the API event bus. All default game event are
     * passed through this event bus.
     */
    public static final EventBus EVENT_BUS = new EventManager();
}
