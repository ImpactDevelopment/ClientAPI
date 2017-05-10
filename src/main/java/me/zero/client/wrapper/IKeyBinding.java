package me.zero.client.wrapper;

/**
 * Wraps KeyBinding to allow direct setting of Keybind states
 *
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
public interface IKeyBinding {

    /**
     * Sets the pressed state of the key
     *
     * @param pressed The pressed state
     */
    void setPressed(boolean pressed);
}
