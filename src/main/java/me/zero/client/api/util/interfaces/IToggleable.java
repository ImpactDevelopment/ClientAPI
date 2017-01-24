package me.zero.client.api.util.interfaces;

/**
 * Simple Toggleable interface
 *
 * @since 1.0
 *
 * Created by Brady on 1/23/2017.
 */
public interface IToggleable {

    /**
     * Called when the state is changed from false to true
     */
    void onEnable();

    /**
     * Called when the state is changed from true to false
     */
    void onDisable();

    /**
     * Toggles the state
     */
    void toggle();

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
