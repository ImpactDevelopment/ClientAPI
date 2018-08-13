package clientapi.command.executor.parser;

import java.util.List;

/**
 * Used to hold parsed input data. Contains the name of the
 * targetted command and the arguments for execution as raw text.
 *
 * @author Brady
 * @since 4/14/2018
 */
public final class ParsedCommandInput {

    /**
     * The specified command header
     */
    private final String command;

    /**
     * The execution arguments expressed as raw text
     */
    private final String[] arguments;

    ParsedCommandInput(String command, String[] arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * @return The specified command header
     */
    public final String getCommand() {
        return this.command;
    }

    /**
     * @return The execution arguments expressed as raw text
     */
    public final String[] getArguments() {
        return this.arguments;
    }
}
