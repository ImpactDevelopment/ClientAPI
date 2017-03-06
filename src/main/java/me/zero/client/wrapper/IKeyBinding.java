package me.zero.client.wrapper;

/**
 * Wraps KeyBinding to allow direct setting of Keybind states
 *
 * @since 1.0
 *
 * Created by Brady on 2/24/2017.
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
