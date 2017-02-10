package me.zero.client.api.util.interfaces;

import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.Map;

/**
 * Marks a type as Bindable.
 *
 * All required methods are already
 * inside of the interface.
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
public interface Bindable {

    /**
     * Stores all of the binds for each instance of {@code IBindable}
     */
    Map<Bindable, Integer> BIND_MAP = new HashMap<>();

    /**
     * Sets the bind that corresponds with this
     * implementation of {@code IBindable}
     *
     * @since 1.0
     *
     * @param bind The bind being set
     */
    default void setBind(int bind) {
        BIND_MAP.put(this, bind);
    }

    /**
     * Retrieves bind from the binding map
     *
     * @since 1.0
     *
     * @return The bind that belongs to this implementation
     */
    default int getBind() {
        BIND_MAP.putIfAbsent(this, Keyboard.KEY_NONE);
        return BIND_MAP.get(this);
    }
}
