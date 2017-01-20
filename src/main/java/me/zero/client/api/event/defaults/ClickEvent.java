package me.zero.client.api.event.defaults;

/**
 * Event called when a Mouse button is pressed outside of a GUI while in-game
 *
 * @see me.zero.client.api.event.defaults.ClickEvent
 *
 * @since 1.0
 *
 * Created by Brady on 1/20/2017.
 */
public class ClickEvent {

    private MouseButton button;

    public ClickEvent(MouseButton button) {
        this.button = button;
    }

    public MouseButton getButton() {
        return this.button;
    }

    public enum MouseButton {
        LEFT(0), RIGHT(1), MIDDLE(2);

        private int id;

        MouseButton(int id) {
            this.id = id;
        }

        public int getID() {
            return this.id;
        }
    }
}
