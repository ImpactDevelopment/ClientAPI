package clientapi.command.exception;

import clientapi.command.Command;
import clientapi.command.executor.argument.ArgumentParser;

/**
 * @author Brady
 * @since 10/18/2017 2:50 PM
 */
public final class ParserException extends CommandException {

    /**
     * The parser involved
     */
    private final ArgumentParser<?> parser;

    /**
     * The raw data that was passed to the parser
     */
    private final String raw;

    /**
     * The type that was expected to be returned
     */
    private final Class<?> expected;

    /**
     * The type that the parser returned
     */
    private final Class<?> returned;

    public ParserException(Command command, ArgumentParser<?> parser, String raw, Class<?> expected, Class<?> returned) {
        super(command, "Parser return type was unexpected");
        this.parser = parser;
        this.raw = raw;
        this.expected = expected;
        this.returned = returned;
    }

    /**
     * @return The parser involved
     */
    public final ArgumentParser<?> getParser() {
        return this.parser;
    }

    /**
     * @return The raw data that was passed to the parser
     */
    public final String getRaw() {
        return this.raw;
    }

    /**
     * @return The type that was expected to be returned
     */
    public final Class<?> getExpected() {
        return this.expected;
    }

    /**
     * @return The type that the parser returned
     */
    public final Class<?> getReturned() {
        return this.returned;
    }
}
