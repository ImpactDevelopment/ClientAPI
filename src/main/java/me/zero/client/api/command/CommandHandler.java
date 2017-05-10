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

import static me.zero.client.api.util.Messages.*;

/**
 * Handles and executes commands.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/13/2017 12:00 PM
 */
public class CommandHandler {

    /**
     * The client associated with the CommandHandler
     */
    private Client client;

    /**
     * The command manager belonging to the Client
     */
    private Manager<Command> manager;

    /**
     * The command prefix
     */
    private String prefix;

    public CommandHandler(Client client) {
        this.client = client;
        this.manager = client.getCommandManager();
        this.prefix = ".";
    }

    @EventHandler
    private final Listener<ChatEvent> chatListener = new Listener<>(event -> {
        if (event.getType() != ChatEvent.Type.SEND)
            return;

        String message = event.getMessage();
        if (message.startsWith(prefix)) {
            message = message.replaceFirst(prefix, "");

            if (run(message)) {
                client.printChatMessage(COMMAND_INVALID);
            }
            event.cancel();
        }
    });

    /**
     * Parses the message and then forwards the
     * data to #run(String, List<String>)
     *
     * @since 1.0
     *
     * @param message The message
     * @return Whether or not parsing was successful
     */
    private boolean run(String message) {
        String[] split = message.split(" ");
        if (split.length == 0)
            return false;

        List<String> args = new ArrayList<>();
        for (int i = 1; i < split.length; i++) {
            String arg = split[i];
            if (arg.length() != 0) {
                args.add(arg);
            }
        }
        return run(split[0], args);
    }

    /**
     * Attempts to run a command from the given chat message
     *
     * @since 1.0
     *
     * @param label The command label
     * @param args The arguments for the command
     * @return Whether or not command execution was successful
     */
    private boolean run(String label, List<String> args) {
        Command command = manager.getData().stream().filter(cmd -> labelMatch(label, cmd)).findFirst().orElse(null);
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

    /**
     * Loops through a Command's labels and sees if the specified
     * header matches one of them.
     *
     * @since 1.0
     *
     * @param label The label being checked
     * @param cmd The command being checked
     * @return Whether or not the label matches
     */
    private boolean labelMatch(String label, Command cmd) {
        return Arrays.stream(cmd.label()).filter(label::equalsIgnoreCase).findFirst().orElse(null) != null;
    }

    /**
     * @since 1.0
     *
     * @return The command prefix
     */
    public String getPrefix() {
        return this.prefix;
    }
}
