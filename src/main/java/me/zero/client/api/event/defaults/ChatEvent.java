package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;
import me.zero.client.api.exception.ActionNotValidException;

/**
 * Called whenever a chat message is sent or received.
 *
 * @since 1.0
 *
 * Created by Brady on 2/10/2017.
 */
public final class ChatEvent extends Cancellable {

    private String message;
    private final Type type;

    public ChatEvent(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public ChatEvent setMessage(String message) {
        if (type == Type.SEND)
            this.message = message;
        else
            throw new ActionNotValidException("You cannot set a message unless it is being sent.");

        return this;
    }

    public enum Type {
        SEND, RECEIVE
    }
}
