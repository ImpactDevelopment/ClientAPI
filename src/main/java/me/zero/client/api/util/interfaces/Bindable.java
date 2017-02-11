package me.zero.client.api.util.interfaces;

import me.zero.client.api.util.keybind.Keybind;

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

    Keybind getBind();
}
