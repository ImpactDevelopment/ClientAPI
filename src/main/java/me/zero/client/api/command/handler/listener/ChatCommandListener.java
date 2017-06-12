package me.zero.client.api.command.handler.listener;

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.client.api.ClientAPI;
import me.zero.client.api.command.Command;
import me.zero.client.api.command.handler.CommandHandler;
import me.zero.client.api.command.executor.sender.CommandSender;
import me.zero.client.api.event.defaults.ChatEvent;
import me.zero.client.api.event.defaults.internal.CommandExecutionEvent;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brady
 * @since 6/11/2017 1:47 PM
 */
public final class ChatCommandListener extends CommandListener implements Helper {

    private static final Pattern REGEX = Pattern.compile("\"([^\"]+)\"|'([^']+)'|([^\\s]+)");

    public ChatCommandListener(CommandHandler handler) {
        super(handler);
    }

    @EventHandler
    private final Listener<ChatEvent.Send> chatListener =  new Listener<>(event -> {
        String raw = event.getRawMessage();

        // If the user sends a message starting with a backslash, then we'll send whatever follows it.
        if (raw.startsWith("\\") && raw.length() > 1) {
            event.setMessage(new TextComponentString(raw.substring(1)));
            return;
        }

        if (raw.startsWith(handler.getPrefix())) {
            try {
                // No matter what, always cancel the message if it starts with the command prefix
                event.cancel();

                // Removed the prefix from the message
                raw = raw.substring(handler.getPrefix().length());

                // Create a matcher to parse the message
                Matcher matcher = REGEX.matcher(raw);

                List<String> matches = new ArrayList<>();
                while (matcher.find()) {
                    matches.add(matcher.group());
                }

                // Only handle the command if there is at least 1 argument group
                if (matches.size() > 0) {
                    Command command = handler.getClient().getCommandManager().getData().stream().filter(cmd -> {
                        for (String header : cmd.headers()) {
                            if (header.equalsIgnoreCase(matches.get(0))) {
                                return true;
                            }
                        }
                        return false;
                    }).findFirst().orElse(null);

                    // Only handle the command if it's found
                    if (command != null) {
                        // Get all matches after the first one, these are treated as arguments
                        String[] args = new String[matches.size() - 1];
                        for (int i = 1; i < matches.size(); i++)
                            args[i - 1] = matches.get(i).replace("\"", "").replace("\'", "");

                        ClientAPI.EVENT_BUS.post(new CommandExecutionEvent(command, CommandSender.from(mc.player), args));
                    }
                }

                // This will be interpreted as an unknown command
                ClientAPI.EVENT_BUS.post(new CommandExecutionEvent(null, null, null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}
