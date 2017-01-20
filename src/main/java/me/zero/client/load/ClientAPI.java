package me.zero.client.load;

import me.zero.client.api.exception.ActionNotSupportedException;

import static me.zero.client.load.ClientAPI.Stage.LOAD;

/**
 * Created by Brady on 1/19/2017.
 */
public class ClientAPI {

    private static ClientAPI api = new ClientAPI();

    private Stage stage = LOAD;

    public void init() {

    }

    public void check(Stage stage, String message) {
        if (this.stage.ordinal() > stage.ordinal())
            throw new ActionNotSupportedException(message);
    }

    public enum Stage {
        LOAD, PRE, INIT, POST, FINISH
    }

    public static ClientAPI getAPI() {
        return api;
    }
}
