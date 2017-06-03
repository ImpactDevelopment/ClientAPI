package me.zero.client.api.command;

import me.zero.client.api.command.exception.CommandInitException;

/**
 * @author Brady
 * @since 5/31/2017 8:55 AM
 */
public abstract class Command implements ICommand {

    private final String[] headers;
    private final String description;
    private final String[] syntax;

    public Command() {
        if (!this.getClass().isAnnotationPresent(Cmd.class))
            throw new RuntimeException(new CommandInitException("@Cmd annotation not found!"));

        Cmd cmd = this.getClass().getAnnotation(Cmd.class);
        this.headers = cmd.headers();
        this.description = cmd.description();
        this.syntax = cmd.syntax();
    }

    @Override
    public String[] headers() {
        return this.headers;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public String[] syntax() {
        return this.syntax;
    }
}
