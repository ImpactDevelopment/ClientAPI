package me.zero.client.api.command.exception;

/**
 * Thrown when a command was expected, but no command headers
 * matched the specified command.
 *
 * @author Brady
 * @since 6/11/2017 3:26 PM
 */
public final class UnknownCommandException extends CommandException {

    public UnknownCommandException() {
        super(null);
    }
}
