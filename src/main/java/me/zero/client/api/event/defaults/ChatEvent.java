package me.zero.client.api.event.defaults;

import me.zero.alpine.type.Cancellable;
import me.zero.client.api.exception.InvalidActionException;
import net.minecraft.util.text.ITextComponent;

/**
 * Called whenever a chat message is sent or received.
 *
 * @author Brady
 * @since 2/10/2017 12:00 PM
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
     * @return The message for this event
     */
    public final String getMessage() {
        return this.message;
    }

    /**
     * Sets this event's chat message, only works if the
     * message is being sent.
     *
     * @param message Message being set
     * @return This Event
     */
    public final ChatEvent setMessage(String message) {
        if (type == Type.SEND)
            this.message = message;
        else
            throw new InvalidActionException("Message cannot be set if type is not SEND");

        return this;
    }

    /**
     * @return The type of event
     */
    public final Type getType() {
        return this.type;
    }

    /**
     * ChatEvent Type
     */
    public enum Type {
        SEND, RECEIVE
    }
}
