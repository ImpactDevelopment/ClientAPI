package me.zero.client.api.command;

/**
 * Stores the usage of a command.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 4/9/2017 12:00PM
 */
public final class CommandUsage {

    /**
     * The Syntax of the usage
     */
    private final String syntax;

    /**
     * The description of the usage
     */
    private final String description;

    public CommandUsage(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }

    /**
     * @since 1.0
     *
     * @return The syntax of the usage
     */
    public final String getSyntax() {
        return this.syntax;
    }

    /**
     * @since 1.0
     *
     * @return The description of the usage
     */
    public final String getDescription() {
        return this.description;
    }
}
