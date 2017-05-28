package me.zero.client.api.command;

/**
 * Stores the usage of a command.
 *
 * @author Brady
 * @since 4/9/2017 12:00 PM
 */
@Deprecated
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
     * @return The syntax of the usage
     */
    public final String getSyntax() {
        return this.syntax;
    }

    /**
     * @return The description of the usage
     */
    public final String getDescription() {
        return this.description;
    }
}
