package me.zero.client.api.util.interfaces;

/**
 * Simple Toggleable interface
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/23/2017 12:00PM
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
    default void toggle() {
        this.setState(!this.getState());
    }

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
