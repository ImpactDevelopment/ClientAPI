package me.zero.client.api.util.keybind;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static me.zero.client.api.util.keybind.Keybind.Action.*;

/**
 * A keybind that is used to create key hooks
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/10/2017 12:00 PM
 */
public final class Keybind {

    /**
     * The List of all Keybind Objects
     */
    private static final List<Keybind> keybinds = new ArrayList<>();

    /**
     * The KeyCode for this Keybind
     */
    private int key;

    /**
     * The consumer that handles various key events
     */
    private final Consumer<Action> consumer;

    public Keybind(int key, Consumer<Action> consumer) {
        this.key = key;
        this.consumer = consumer;
        Keybind.keybinds.add(this);
    }

    /**
     * @since 1.0
     *
     * @param key The key code being set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * @since 1.0
     *
     * @return The key code of this bind
     */
    public int getKey() {
        return this.key;
    }

    /**
     * Called when a key's state has been
     * switched from released to pressed
     *
     * @since 1.0
     */
    public final void onClick() {
        consumer.accept(CLICK);
    }

    /**
     * Called when a key is pressed
     *
     * @since 1.0
     */
    public final void onPress() {
        consumer.accept(PRESS);
    }

    /**
     * Claled when a key is released
     *
     * @since 1.0
     */
    public final void onRelease() {
        consumer.accept(RELEASE);
    }

    /**
     * @since 1.0
     *
     * @return The list of all Keybind Objects
     */
    public static List<Keybind> getKeybinds() {
        return Keybind.keybinds;
    }

    /**
     * Keybind type
     */
    public enum Type {
        TOGGLE, HOLD
    }

    /**
     * Type of Key Action
     */
    public enum Action {
        CLICK, PRESS, RELEASE
    }
}
