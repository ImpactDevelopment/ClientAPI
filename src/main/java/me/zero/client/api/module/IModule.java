package me.zero.client.api.module;

import me.zero.client.api.util.interfaces.Bindable;
import me.zero.client.api.util.interfaces.Nameable;
import me.zero.client.api.util.interfaces.Toggleable;

/**
 * Base for Module
 *
 * @see me.zero.client.api.module.Module
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
interface IModule extends Toggleable, Bindable, Nameable {

    /**
     * @since 1.0
     *
     * @return The type of the Module
     */
    String getType();
}
