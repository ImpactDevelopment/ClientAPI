package me.zero.client.api.command.parse;

/**
 * Represents an argument
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/13/2017 12:00PM
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
     * @since 1.0
     *
     * @return The label
     */
    public final String getLabel() {
        return this.label;
    }

    /**
     * @since 1.0
     *
     * @return The type
     */
    public final Class<T> getType() {
        return this.type;
    }

    /**
     * @since 1.0
     *
     * @return The parser
     */
    public final ArgumentParser<T> getParser() {
        return this.parser;
    }
}
