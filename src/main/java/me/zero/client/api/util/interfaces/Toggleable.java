package me.zero.client.api.util.interfaces;

/**
 * Simple Toggleable interface
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
 */
public interface Toggleable {

    /**
     * Called when the state is changed from false to true
     */
    default void onEnable() {}

    /**
     * Called when the state is changed from true to false
     */
    default void onDisable() {}

    /**
     * Toggles the state
     */
    default void toggle() {
        this.setState(!this.getState());
    }

    /**
     * Directly sets the state
     *
     * @param state The new state
     */
    void setState(boolean state);

    /**
     * @return The current state
     */
    boolean getState();
}
