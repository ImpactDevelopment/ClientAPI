package clientapi.util.io;

/**
 * @author Brady
 * @since 4/13/2018 4:59 PM
 */
public enum MouseKeyTracker {

    INSTANCE;

    /**
     * Array that maps the mouse button id (the index in the array) to
     * the state of that button. Used to track when the state of the
     * button changes so that a ClickEvent is correctly dispatched.
     */
    private boolean[] mouseKeyStates = new boolean[0];

    public final boolean wasButtonPressed(int button, boolean newState) {
        // Check if the state array needs to be expanded
        if (mouseKeyStates.length <= button) {
            // Create a new array with the required size
            boolean[] expandedArray = new boolean[button + 1];
            // Copy the elements from the old array to the new one
            System.arraycopy(mouseKeyStates, 0, expandedArray, 0, mouseKeyStates.length);
            // Set the state array to the expanded one
            mouseKeyStates = expandedArray;
        }

        // Return true if the mouse is pressed in the new state and wasn't in the old state
        return newState && !mouseKeyStates[button];
    }
}
