package me.zero.client.wrapper;

/**
 * Wraps KeyBinding to allow direct setting of Keybind states
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/24/2017 12:00PM
 */
public interface IKeyBinding {

    /**
     * Sets the pressed state of the key
     *
     * @since 1.0
     *
     * @param pressed The pressed state
     */
    void setPressed(boolean pressed);
}
