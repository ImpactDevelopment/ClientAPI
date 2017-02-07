package me.zero.client.load;

import me.zero.client.api.Client;
import me.zero.client.api.exception.ActionNotValidException;
import me.zero.client.api.exception.UnexpectedOutcomeException;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import me.zero.client.load.inject.ClientTweaker;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static me.zero.client.load.ClientAPI.Stage.*;

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

    /**
     * Current init stage
     */
    Stage stage = LOAD;

    private ClientAPI() {}

    /**
     * Runs the Init process
     *
     * @since 1.0
     *
     * @param tweaker The tweaker loaded by the game
     */
    public void init(ClientTweaker tweaker) {
        String clientPath = null;

        // Clean this up
        // Maybe make a system for parsing arguments?
        List<String> args = tweaker.getArguments();
        int index = -1;
        for (String arg : args) {
            if (arg.equalsIgnoreCase("--clientPath")) {
                int i = args.indexOf(arg) + 1;
                if (i > 0 && i < args.size()) {
                    index = i - 1;
                    clientPath = args.get(i);
                }
            }
        }
        if (index >= 0) {
            args.remove(index);
            args.remove(index);
        }

        if (clientPath == null)
            throw new UnexpectedOutcomeException("Client File undefined");

        File clientFile = new File(clientPath);

        if (!clientFile.exists())
            throw new UnexpectedOutcomeException("Client File doesn't exist");

        if (!clientFile.getAbsolutePath().endsWith(".jar"))
            throw new UnexpectedOutcomeException("Client File isn't a jar file");

        Logger.instance.log(Level.INFO, "Loading Client");

        try {
            this.loader = new ClientLoader(clientFile);
            Logger.instance.log(Level.INFO, "Loaded Client");
            Client client = loader.getDiscoveredClient();

            if (client == null)
                throw new UnexpectedOutcomeException("Unable to load Client, Client is null!");

            Logger.instance.log(Level.INFO, "Running Client Init");
            loader.loadClient();
        } catch (IOException e) {
            throw new UnexpectedOutcomeException("Error while loading client, " + e.getClass().getCanonicalName());
        }
    }

    /**
     * Checks if the specified stage will be run after
     * the current one. If the specified stage has
     * already been run, an {@code ActionNotValidException}
     * will be thrown.
     *
     * @see me.zero.client.api.exception.ActionNotValidException
     *
     * @since 1.0
     *
     * @param stage The stage being checked
     * @param message The message that will be
     */
    public void check(Stage stage, String message) {
        if (this.stage.ordinal() > stage.ordinal())
            throw new ActionNotValidException(message);
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
     * Init stage
     */
    public enum Stage {
        LOAD, PRE, INIT, POST, FINISH
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
