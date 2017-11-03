package clientapi.event.defaults.game.core;

/**
 * Generic input event.
 *
 * The keycode will be equal to its respective value
 * in {@code Keyboard} if the input is from the keyboard.
 *
 * The keycode will be equal to {@code button - 100}
 * if the input is from the mouse.
 *
 * @see KeyEvent
 * @see ClickEvent
 *
 * @author Brady
 * @since 11/3/2017 11:06 AM
 */
abstract class InputEvent {

    /**
     * The key that was pressed or clicked
     */
    protected final int key;

    InputEvent(int key) {
        this.key = key;
    }

    /**
     * @return The key that was pressed or clicked
     */
    public final int getKey() {
        return this.key;
    }
}
