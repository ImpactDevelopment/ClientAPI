package clientapi.command.exception;

import clientapi.command.Command;

import java.lang.reflect.Type;

/**
 * Thrown when there isn't a parser for an expected argument type.
 *
 * @author Brady
 * @since 1/4/2018 11:06 AM
 */
public class InvalidParserException extends CommandException {

    /**
     * The argument that was given for the parameter
     * with a type that doesn't have a defined parser.
     */
    private String argument;

    /**
     * The {@code Type} that doesn't have a defined parser.
     */
    private Type type;

    public InvalidParserException(Command command, String argument, Type type) {
        super(command);
        this.argument = argument;
        this.type = type;
    }

    /**
     * @return The argument given for the invalid command parameter.
     */
    public final String getArgument() {
        return this.argument;
    }

    /**
     * @return The {@code Type} that doesn't have a defined parser.
     */
    public final Type getType() {
        return this.type;
    }
}
