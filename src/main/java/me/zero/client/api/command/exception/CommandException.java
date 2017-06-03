package me.zero.client.api.command.exception;

/**
 * Superclass for all exceptions in the
 * {@code me.zero.client.api.exception.command} package.
 *
 * @author Brady
 * @since 5/30/2017 11:39 AM
 */
public class CommandException extends Exception {

    public CommandException() {}

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Object... args) {
        super(String.format(message, args));
    }
}
