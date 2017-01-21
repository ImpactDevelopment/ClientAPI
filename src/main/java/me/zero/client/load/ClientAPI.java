package me.zero.client.load;

import me.zero.client.api.exception.ActionNotValidException;
import me.zero.client.api.transformer.reference.MethodReference;

import static me.zero.client.load.ClientAPI.Stage.LOAD;

/**
 * Client API base, handles Client Loading
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public class ClientAPI {

    /**
     * Instance of the API
     */
    private static ClientAPI api = new ClientAPI();

    /**
     * Version of this Release
     */
    private static final double VERSION = 1.0;

    /**
     * Current init stage
     */
    private Stage stage = LOAD;

    /**
     * Runs the Init process
     *
     * @since 1.0
     */
    public void init() {
        if (stage != LOAD) return;

        // Discover Clients
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
