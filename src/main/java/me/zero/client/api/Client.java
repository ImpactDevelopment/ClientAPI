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
     * Called after the game has initialized.
     *
     * @see me.zero.client.api.module.Module
     * @see me.zero.client.api.module.plugin.Plugin
     *
     * @param loader The ClientLoader used to load the client from within the game. (Not LaunchClassLoader)
     *
     * @since 1.0
     */
    public abstract void onInit(ClientLoader loader);
}
