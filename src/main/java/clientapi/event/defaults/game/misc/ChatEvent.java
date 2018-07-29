/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.event.defaults.game.misc;

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
        return this.message.getString();
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
