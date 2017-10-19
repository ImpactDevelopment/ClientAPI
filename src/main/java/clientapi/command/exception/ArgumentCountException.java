package clientapi.command.exception;

import clientapi.command.Command;

/**
 * @author Brady
 * @since 10/18/2017 11:07 AM
 */
public final class ArgumentCountException extends CommandException {

    public ArgumentCountException(Command command) {
        super(command, "An invalid amount of arguments were provided");
    }

    public ArgumentCountException(Command command, int found, int expected) {
        super(command, found > expected ? "Too many arguments provided" : found < expected ? "Not enough arguments provided" : "Correct amount of arguments provided, unknown cause.");
    }
}
