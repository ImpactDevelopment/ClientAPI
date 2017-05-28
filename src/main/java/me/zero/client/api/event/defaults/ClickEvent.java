package me.zero.client.api.event.defaults;

import net.minecraft.client.Minecraft;

/**
 * Event called when a Mouse button is pressed
 * outside of a gui screen while in-game. The
 * only mouse buttons that are supported are the
 * present in the {@code MouseButton} enum.
 * (Left, Right, Middle)
 *
 * @see KeyEvent
 * @see Minecraft#clickMouse()
 * @see Minecraft#rightClickMouse()
 * @see Minecraft#middleClickMouse()
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
         * @return The ID of the Mouse Button
         */
        public final int getID() {
            return this.id;
        }
    }
}
