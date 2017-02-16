package me.zero.client.api.util.interfaces;

/**
 * Action, can be started and stopped
 *
 * @since 1.0
 *
 * Created by Brady on 2/16/2017.
 */
public interface Action {

    /**
     * Starts the action
     *
     * @since 1.0
     */
    void start();

    /**
     * Stops the action
     *
     * @since 1.0
     */
    void stop();
}
