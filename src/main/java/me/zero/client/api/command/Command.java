package me.zero.client.api.command;

import me.zero.client.api.command.parse.CommandArg;
import me.zero.client.api.command.parse.CommandContext;

/**
 * A command
 *
 * @author Brady
 * @since 2/13/2017 12:00 PM
 */
@Deprecated
public interface Command {

    /**
     * @return The list of command headers
     */
    String[] label();

    /**
     * @return The usage explaination
     */
    String description();

    /**
     * @return The arguments that the command needs to execute
     */
    CommandArg[] arguments();

    /**
     * @return The usage of this command
     */
    CommandUsage[] usage();

    /**
     * Executes this command with the given context
     *
     * @param context The command context
     */
    void execute(CommandContext context);
}
