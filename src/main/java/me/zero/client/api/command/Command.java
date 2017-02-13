package me.zero.client.api.command;

import me.zero.client.api.command.args.CommandArg;

/**
 * A command
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public interface Command extends CommandExecutor {

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
}
