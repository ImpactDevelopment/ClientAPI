package me.zero.client.api;

import me.zero.client.load.ClientLoader;

/**
 * The base for all Clients
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public abstract class Client extends ClientBase {

    /**
     * Called after the game has initialized
     * Used for Creation of managers,
     * Module Registry and Plugin loading
     *
     * @see #postInit()
     * @see me.zero.client.api.module.Module
     * @see me.zero.client.api.module.plugin.Plugin
     *
     * @param loader The ClientLoader used to load the client from within the game. (Not LaunchClassLoader)
     *
     * @since 1.0
     */
    public abstract void onInit(ClientLoader loader);

    /**
     * Last init call once everything else is done
     * Used for GUI things such as Fonts and Click/Tab GUIs
     *
     * @see #onInit(ClientLoader)
     * @see me.zero.client.api.gui.tab.ITabGui
     *
     * @since 1.0
     */
    public abstract void postInit();
}
