package clientapi.command.exception;

import clientapi.command.Command;

/**
 * @author Brady
 * @since 10/18/2017 3:06 PM
 */
public class CommandExecutionException extends CommandException {

    public CommandExecutionException(Command command) {
        super(command);
    }
}
