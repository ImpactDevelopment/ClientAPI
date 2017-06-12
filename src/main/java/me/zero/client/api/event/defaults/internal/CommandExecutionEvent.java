package me.zero.client.api.event.defaults.internal;

import me.zero.client.api.command.Command;
import me.zero.client.api.command.executor.sender.CommandSender;

/**
 * @author Brady
 * @since 6/11/2017 9:42 AM
 */
public final class CommandExecutionEvent {

    /**
     * The command being executed
     */
    private final Command command;

    /**
     * Sender that executed the command
     */
    private final CommandSender sender;

    /**
     * Arguments to be passed to the command
     */
    private final String[] arguments;

    public CommandExecutionEvent(Command command, CommandSender sender, String[] arguments) {
        this.command = command;
        this.sender = sender;
        this.arguments = arguments;
    }

    public final Command getCommand() {
        return this.command;
    }

    public final CommandSender getSender() {
        return this.sender;
    }

    public final String[] getArguments() {
        return this.arguments;
    }
}
