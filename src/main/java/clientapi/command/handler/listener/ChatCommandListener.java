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

package clientapi.command.handler.listener;

import clientapi.command.Command;
import clientapi.command.executor.ExecutionContext;
import clientapi.command.executor.parser.CommandInputParser;
import clientapi.command.executor.parser.ParsedCommandInput;
import clientapi.command.handler.CommandHandler;
import clientapi.command.sender.CommandSender;
import clientapi.event.defaults.game.misc.ChatEvent;
import clientapi.util.ClientAPIUtils;
import clientapi.util.interfaces.MinecraftAccessible;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.util.text.TextComponentString;

import java.util.Optional;

/**
 * Command listener setup to listen to chat commands
 * sent through client-sided input
 *
 * @author Brady
 * @since 6/11/2017
 */
public final class ChatCommandListener extends CommandListener implements MinecraftAccessible {

    public ChatCommandListener(CommandHandler handler) {
        super(handler);
    }

    @EventHandler
    private final Listener<ChatEvent.Send> chatListener =  new Listener<>(event -> {
        String raw = event.getRawMessage();

        // If the user sends a message starting with a backslash, then we'll send whatever follows it.
        // So if our command prefix was "." then sending "\.bind" would send ".bind" in chat.
        // This replaces the need for the standard ".say" command that is present in most clients.
        if (raw.startsWith("\\") && raw.length() > 1) {
            event.setMessage(new TextComponentString(raw.substring(1)));
            return;
        }

        // If the message is a command
        if (raw.startsWith(handler.getPrefix())) {
            // No matter what, always cancel the message if it starts with the command prefix
            event.cancel();

            // Removed the prefix from the message
            raw = raw.substring(handler.getPrefix().length());

            // Parse the input, if the optional is empty that indicates that
            // the raw data that was input into
            Optional<ParsedCommandInput> input = CommandInputParser.INSTANCE.parseCommandInput(raw);

            // Execute the command if the input was valid
            if (input.isPresent()) {
                // Find a command that has a header matching the input command header
                Command command = handler.getCommandManager().stream()
                        .filter(cmd -> ClientAPIUtils.containsIgnoreCase(cmd.getHandles(), input.get().getCommand()))
                        .findFirst().orElse(null);

                // Only handle the command if it's found
                if (command != null) {
                    this.handler.execute(
                            command,
                            ExecutionContext.of(CommandSender.from(mc.player), this.handler),
                            input.get().getArguments()
                    );
                    return;
                }
            }
            this.handler.execute(null, null, null);
        }
    });
}
