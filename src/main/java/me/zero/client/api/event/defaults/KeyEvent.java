package me.zero.client.api.event.defaults;

/**
 * Event called when a Key is pressed outside of a GUI while in-game
 *
 * @see me.zero.client.api.event.defaults.ClickEvent
 *
 * @author Brady
 * @since 1/20/2017 12:00 PM
 */
public final class KeyEvent {

    /**
     * Key code that belongs to the pressed key
     */
    private final int key;

    /**
     * Creates a new instance of KeyEvent.
     *
     * @param key - The key code for the key that was pressed
     */
    public KeyEvent(int key) {
        this.key = key;
    }

    /**
     * @return The key code that corresponds to the pressed key
     */
    public final int getKey() {
        return this.key;
    }
}
