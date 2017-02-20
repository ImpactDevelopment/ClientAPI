package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;
import me.zero.client.api.exception.ActionNotValidException;
import net.minecraft.util.text.ITextComponent;

/**
 * Called whenever a chat message is sent or received.
 *
 * @since 1.0
 *
 * Created by Brady on 2/10/2017.
 */
public final class ChatEvent extends Cancellable {

    /**
     * The ChatEvent's message
     */
    private String message;

    /**
     * The Type of event (SEND/RECEIVE)
     */
    private final Type type;

    public ChatEvent(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public ChatEvent(ITextComponent message, Type type) {
        this(message.getFormattedText(), type);
    }

    /**
     * @since 1.0
     *
     * @return The message for this event
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets this event's chat message, only works if the
     * message is being sent.
     *
     * @since 1.0
     *
     * @param message Message being set
     * @return This Event
     */
    public ChatEvent setMessage(String message) {
        if (type == Type.SEND)
            this.message = message;
        else
            throw new ActionNotValidException("You cannot set a message unless it is being sent.");

        return this;
    }

    /**
     * @since 1.0
     *
     * @return The type of event
     */
    public Type getType() {
        return this.type;
    }

    /**
     * ChatEvent Type
     */
    public enum Type {
        SEND, RECEIVE
    }
}
