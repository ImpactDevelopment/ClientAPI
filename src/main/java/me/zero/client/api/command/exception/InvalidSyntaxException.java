package me.zero.client.api.command.exception;

import me.zero.client.api.command.Command;

/**
 * Thrown by implementations of Command if the provided
 * arguments aren't valid. Hence, invalid synax.
 *
 * @author Brady
 * @since 5/31/2017 9:13 AM
 */
public final class InvalidSyntaxException extends CommandException {

    public InvalidSyntaxException(Command command) {
        super(command, "Invalid Command Syntax");
    }
}
