package me.zero.client.api.command.parse;

/**
 * Represents an argument
 *
 * @author Brady
 * @since 2/13/2017 12:00 PM
 */
public final class CommandArg<T> {

    /**
     * The label used to refer to the argument
     */
    private final String label;

    /**
     * The argument type
     */
    private final Class<T> type;

    /**
     * The parser used to get the type from a String
     */
    private final ArgumentParser<T> parser;

    CommandArg(String label, Class<T> type, ArgumentParser<T> parser) {
        this.label = label;
        this.type = type;
        this.parser = parser;
    }

    /**
     * @return The label
     */
    public final String getLabel() {
        return this.label;
    }

    /**
     * @return The type
     */
    public final Class<T> getType() {
        return this.type;
    }

    /**
     * @return The parser
     */
    public final ArgumentParser<T> getParser() {
        return this.parser;
    }
}
