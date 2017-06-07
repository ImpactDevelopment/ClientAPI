package me.zero.client.api.command.exception;

import me.zero.client.api.command.Command;

/**
 * Superclass for all exceptions in the
 * {@code me.zero.client.api.exception.command} package.
 *
 * @author Brady
 * @since 5/30/2017 11:39 AM
 */
public class CommandException extends Exception {

    /**
     * Command that encountered an exception
     */
    private final Command command;

    public CommandException(Command command) {
        this.command = command;
    }

    public CommandException(Command command, String message) {
        super(message);
        this.command = command;
    }

    public CommandException(Command command, String message, Object... args) {
        super(String.format(message, args));
        this.command = command;
    }

    /**
     * @return The command that encountered an exception
     */
    public final Command getCommand() {
        return this.command;
    }
}
