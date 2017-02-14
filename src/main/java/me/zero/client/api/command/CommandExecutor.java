package me.zero.client.api.command;

import me.zero.client.api.command.parse.CommandContext;

/**
 * An executor for a Command
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
interface CommandExecutor {

    void execute(CommandContext context);
}
