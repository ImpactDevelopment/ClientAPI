package me.zero.client.api.command;

import me.zero.client.api.Client;
import me.zero.client.api.command.parse.CommandContext;
import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.defaults.ChatEvent;
import me.zero.client.api.manage.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles and executes commands.
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public class CommandHandler {

    private Client client;
    private Manager<Command> manager;

    public CommandHandler(Client client) {
        this.client = client;
        this.manager = client.getCommandManager();
    }

    @EventHandler
    private Listener<ChatEvent> chatListener = new Listener<>(event -> {
        if (event.getType() != ChatEvent.Type.SEND)
            return;

        String message = event.getMessage();
        if (message.startsWith(".")) {
            message = message.replaceFirst(".", "");

            event.setCancelled(run(message));
        }
    });

    public boolean run(String message) {
        String[] split = message.split(" ");
        if (split.length == 0)
            return false;

        String header = split[0];
        List<String> args = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            String arg = split[i];
            if (arg.length() != 0) {
                args.add(arg);
            }
        }
        return run(header, args);
    }

    private boolean run(String header, List<String> args) {
        Command command = manager.getData().stream().filter(cmd -> labelMatch(header, cmd)).findFirst().orElse(null);
        if (command == null)
            return false;

        CommandContext context = new CommandContext(command, args.toArray(new String[0]));

        if (!context.isComplete()) {
            context.getError().forEach(client::printChatMessage);
            return true;
        }

        command.execute(context);
        return true;
    }

    private boolean labelMatch(String label, Command cmd) {
        return Arrays.stream(cmd.label()).filter(label::equalsIgnoreCase).findFirst().orElse(null) != null;
    }
}
