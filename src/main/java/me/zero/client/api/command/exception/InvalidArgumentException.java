package me.zero.client.api.command.exception;

import me.zero.client.api.command.Command;

/**
 * @author Brady
 * @since 6/7/2017 9:34 AM
 */
public final class InvalidArgumentException extends CommandException {

    /**
     * Array of inputted arguments
     */
    private final String[] args;

    /**
     * Index of the bag argument
     */
    private final int badArg;

    /**
     * Expected type
     */
    private final Class<?> expected;

    public InvalidArgumentException(Command command, String[] args, int badArg, Class<?> expected) {
        super(command);
        this.args = args;
        this.badArg = badArg;
        this.expected = expected;
    }

    /**
     * @return Array of inputted arguments
     */
    public final String[] getArgs() {
        return this.args;
    }

    /**
     * @return Index of the bad argument
     */
    public final int getBadArg() {
        return this.badArg;
    }

    /**
     * Returns the expected class type of the given argument.
     *
     * @return The expected type
     */
    public Class<?> getExpectedType() {
        return this.expected;
    }
}
