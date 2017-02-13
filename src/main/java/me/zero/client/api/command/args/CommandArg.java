package me.zero.client.api.command.args;

/**
 * Represents an argument
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public class CommandArg<T> {

    private String label;
    private Class<T> type;
    private ArgumentParser<T> parser;
    private boolean optional;

    CommandArg(String label, Class<T> type, ArgumentParser<T> parser, boolean optional) {
        this.label = label;
        this.type = type;
        this.parser = parser;
        this.optional = optional;
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

    public boolean isOptional() {
        return this.optional;
    }
}
