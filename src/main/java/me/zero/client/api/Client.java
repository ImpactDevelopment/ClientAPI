package me.zero.client.api;

/**
 * The base for all Clients
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/19/2017 12:00PM
 */
public abstract class Client extends ClientBase {

    /**
     * Called after the game has initialized.
     *
     * @see me.zero.client.api.module.Module
     * @see me.zero.client.api.module.plugin.Plugin
     *
     * @param info The ClientInfo object from the client.json
     *
     * @since 1.0
     */
    public abstract void onInit(ClientInfo info);
}
