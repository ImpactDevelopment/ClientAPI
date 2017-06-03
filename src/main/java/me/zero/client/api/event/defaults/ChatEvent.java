package me.zero.client.api.event.defaults;

import me.zero.alpine.type.Cancellable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

/**
 * Called whenever a chat message is sent or received.
 *
 * @see Send
 * @see Receive
 *
 * @author Brady
 * @since 2/10/2017 12:00 PM
 */
public class ChatEvent extends Cancellable {

    /**
     * The ChatEvent's message
     */
    protected ITextComponent message;

    private ChatEvent(ITextComponent message) {
        this.message = message;
    }

    /**
     * Sets the message being sent/received
     *
     * @param message The new message
     */
    public void setMessage(ITextComponent message) {
        this.message = message;
    }

    /**
     * @return The message
     */
    public final ITextComponent getMessage() {
        return this.message;
    }

    /**
     * @return The raw message, unformatted text component
     */
    public final String getRawMessage() {
        return this.message.getUnformattedText();
    }

    /**
     * Called when a chat message is sent
     */
    public static final class Send extends ChatEvent {

        public Send(String message) {
            super(new TextComponentString(message));
        }
    }

    /**
     * Called when a chat message is received
     */
    public static final class Receive extends ChatEvent {

        public Receive(ITextComponent message) {
            super(message);
        }
    }
}
