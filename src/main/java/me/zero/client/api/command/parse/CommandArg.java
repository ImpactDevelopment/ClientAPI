package me.zero.client.api.command.parse;

/**
 * Represents an argument
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public class CommandArg<T> {

    private final String label;
    private final Class<T> type;
    private final ArgumentParser<T> parser;

    CommandArg(String label, Class<T> type, ArgumentParser<T> parser) {
        this.label = label;
        this.type = type;
        this.parser = parser;
    }

    public String getLabel() {
        return this.label;
    }

    public Class<T> getType() {
        return this.type;
    }

    public ArgumentParser<T> getParser() {
        return this.parser;
    }
}
