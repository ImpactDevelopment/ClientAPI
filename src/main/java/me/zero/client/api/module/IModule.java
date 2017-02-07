package me.zero.client.api.module;

import me.zero.client.api.util.interfaces.IBindable;
import me.zero.client.api.util.interfaces.IToggleable;

/**
 * Base for Module
 *
 * @see me.zero.client.api.module.Module
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
interface IModule extends IToggleable, IBindable {

    /**
     * @since 1.0
     *
     * @return The name of the Module
     */
    String getName();

    /**
     * @since 1.0
     *
     * @return The description of the Module
     */
    String getDescription();

    /**
     * @since 1.0
     *
     * @return The type of the Module
     */
    String getType();
}
