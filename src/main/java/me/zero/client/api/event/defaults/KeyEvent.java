package me.zero.client.api.event.defaults;

/**
 * Event called when a Key is pressed outside of a GUI while in-game
 *
 * @see me.zero.client.api.event.defaults.ClickEvent
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public class KeyEvent {

    /**
     * Key code that belongs to the pressed key
     */
    private int key;

    /**
     * Creates a new instance of KeyEvent.
     *
     * @param key - The key code for the key that was pressed
     */
    public KeyEvent(int key) {
        this.key = key;
    }

    /**
     * @since 1.0
     *
     * @return The key code that corresponds to the pressed key
     */
    public int getKey() {
        return this.key;
    }
}
