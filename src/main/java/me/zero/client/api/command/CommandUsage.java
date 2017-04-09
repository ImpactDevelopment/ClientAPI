package me.zero.client.api.command;

/**
 * Stores the usage of a command.
 *
 * @since 1.0
 *
 * Created by Brady on 4/9/2017.
 */
public class CommandUsage {

    /**
     * The Syntax of the usage
     */
    public String syntax;

    /**
     * The description of the usage
     */
    public String description;

    public CommandUsage(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }

    /**
     * @since 1.0
     *
     * @return The syntax of the usage
     */
    public String getSyntax() {
        return this.syntax;
    }

    /**
     * @since 1.0
     *
     * @return The description of the usage
     */
    public String getDescription() {
        return this.description;
    }
}
