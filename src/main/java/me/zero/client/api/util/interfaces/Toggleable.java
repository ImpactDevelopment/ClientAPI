package me.zero.client.api.util.interfaces;

/**
 * Simple Toggleable interface
 *
 * @since 1.0
 *
 * Created by Brady on 1/23/2017.
 */
public interface Toggleable {

    /**
     * @since 1.0
     *
     * Called when the state is changed from false to true
     */
    default void onEnable() {}

    /**
     * @since 1.0
     *
     * Called when the state is changed from true to false
     */
    default void onDisable() {}

    /**
     * @since 1.0
     *
     * Toggles the state
     */
    void toggle();

    /**
     * @since 1.0
     *
     * Directly sets the state
     *
     * @param state The new state
     */
    void setState(boolean state);

    /**
     * @since 1.0
     *
     * @return The current state
     */
    boolean getState();
}
