package me.zero.client.api.event.defaults;

/**
 * Event called when a Mouse button is pressed outside of a GUI while in-game
 *
 * @see me.zero.client.api.event.defaults.ClickEvent
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/20/2017 12:00 PM
 */
public final class ClickEvent {

    /**
     * The Mouse button that was clicked
     */
    private final MouseButton button;

    public ClickEvent(MouseButton button) {
        this.button = button;
    }

    /**
     * @since 1.0
     *
     * @return The button clicked
     */
    public final MouseButton getButton() {
        return this.button;
    }

    /**
     * Types of Mouse Buttons
     */
    public enum MouseButton {
        LEFT(0), RIGHT(1), MIDDLE(2);

        /**
         * Mouse Button ID
         */
        private int id;

        MouseButton(int id) {
            this.id = id;
        }

        /**
         * @since 1.0
         *
         * @return The ID of the Mouse Button
         */
        public final int getID() {
            return this.id;
        }
    }
}
