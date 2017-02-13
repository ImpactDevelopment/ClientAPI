package me.zero.client.api.command;

/**
 * An executor for a Command
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
interface CommandExecutor {

    void execute(String message, String[] args);
}
