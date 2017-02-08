package me.zero.client.load;

import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.util.ClientUtils;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import me.zero.client.load.inject.ClientTweaker;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Client API base, handles Client Loading
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public final class ClientAPI {

    /**
     * Instance of the API
     */
    private static ClientAPI api = new ClientAPI();

    /**
     * Version of this Release
     */
    private static final double VERSION = 1.0;

    /**
     * Current Client Loader
     */
    private ClientLoader loader;

    private ClientAPI() {}

    /**
     * Runs the Init process
     *
     * @since 1.0
     *
     * @param tweaker The tweaker loaded by the game
     */
    public void init(ClientTweaker tweaker) {
        String clientPath = ClientUtils.getClientPath(tweaker.getArguments());

        if (clientPath == null)
            throw new UnexpectedOutcomeException("Client File undefined");

        File clientFile = new File(clientPath);

        if (!clientFile.exists())
            throw new UnexpectedOutcomeException("Client File doesn't exist");

        if (!clientFile.getAbsolutePath().endsWith(".jar"))
            throw new UnexpectedOutcomeException("Client File isn't a jar file");

        Logger.instance.log(Level.INFO, "Initializing LaunchClassLoader ClientLoader");

        try {
            this.loader = new ClientLoader(clientFile);
            Logger.instance.log(Level.INFO, "Initialized ClientLoader");
        } catch (IOException e) {
            throw new UnexpectedOutcomeException("Error while loading client, " + e.getClass().getCanonicalName());
        }
    }

    /**
     * Returns the Client Loader
     *
     * @since 1.0
     *
     * @return Client Loader
     */
    public ClientLoader getLoader() {
        return this.loader;
    }

    /**
     * Returns the Instance of the Client API.
     * Instance is initialized upon first static call.
     *
     * @since 1.0
     *
     * @return The Instance of the Client API
     */
    public static ClientAPI getAPI() {
        return api;
    }

    /**
     * Getter for the Current Release Version.
     *
     * @since 1.0
     *
     * @return Current Release Version
     */
    public static double getVersion() {
        return VERSION;
    }
}
