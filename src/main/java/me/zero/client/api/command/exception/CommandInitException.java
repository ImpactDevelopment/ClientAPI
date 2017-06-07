package me.zero.client.api.command.exception;

import me.zero.client.api.command.Command;

/**
 * Called when the initialization of a {@code Command}
 * fails.c
 *
 * @author Brady
 * @since 5/31/2017 9:08 AM
 */
public final class CommandInitException extends CommandException {

    public CommandInitException(Command command, String message) {
        super(command, message);
    }
}
