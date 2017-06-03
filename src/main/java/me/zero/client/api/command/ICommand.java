package me.zero.client.api.command;

import me.zero.client.api.command.exception.CommandException;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.client.network.NetworkPlayerInfo;

/**
 * Base for Command
 *
 * @see Command
 *
 * @author Brady
 * @since 5/30/2017 11:38 AM
 */
interface ICommand extends Helper {

    /**
     * Executes this command from the specified player
     * represented by its {@code NetworkPlayerInfo}. This
     * can be used to allow other players to execute commands.
     *
     * @param sender The NetworkPlayerInfo that executed this command
     * @param arguments The arguments that
     */
    void execute(NetworkPlayerInfo sender, String[] arguments) throws CommandException;

    /**
     * Returns the array of possible command "headers"
     * that can be used to execute this command. At least
     * one must be supplied, if not, then a
     *
     * @return The list of possible command headers
     */
    String[] headers();

    /**
     * Returns the intended purpose of the command, a
     * brief description of the usage.
     *
     * @return The description of the command usage.
     */
    String description();

    /**
     * Returns the syntax of the command. The syntax
     * gives instructions on how the command may be executed.
     * Commands without arguments can provide an empty array.
     *
     * @return The syntax of the command.
     */
    String[] syntax();
}
