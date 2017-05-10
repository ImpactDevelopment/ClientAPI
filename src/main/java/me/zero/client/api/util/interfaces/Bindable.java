package me.zero.client.api.util.interfaces;

import me.zero.client.api.util.keybind.Keybind;

/**
 * Marks a type as Bindable.
 *
 * All required methods are already
 * inside of the interface.
 *
 * @author Brady
 * @since 1/21/2017 12:00 PM
 */
public interface Bindable {

    /**
     * @return The bind associated with this object
     */
    Keybind getBind();
}
