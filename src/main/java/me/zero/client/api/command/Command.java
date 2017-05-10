package me.zero.client.api.command;

import me.zero.client.api.command.parse.CommandArg;
import me.zero.client.api.command.parse.CommandContext;

/**
 * A command
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/13/2017 12:00 PM
 */
public interface Command {

    /**
     * @since 1.0
     *
     * @return The list of command headers
     */
    String[] label();

    /**
     * @since 1.0
     *
     * @return The usage explaination
     */
    String description();

    /**
     * @since 1.0
     *
     * @return The arguments that the command needs to execute
     */
    CommandArg[] arguments();

    /**
     * @since 1.0
     *
     * @return The usage of this command
     */
    CommandUsage[] usage();

    /**
     * Executes this command with the given context
     *
     * @since 1.0
     *
     * @param context The command context
     */
    void execute(CommandContext context);
}
