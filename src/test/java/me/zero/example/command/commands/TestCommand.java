package me.zero.example.command.commands;

import clientapi.api.command.Cmd;
import clientapi.api.command.Command;
import clientapi.api.command.exception.CommandException;
import clientapi.api.command.executor.sender.CommandSender;
import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;

/**
 * @author Brady
 * @since 5/31/2017 6:18 PM
 */
@Cmd(headers = { "test", "example"}, description = "Test Command")
public final class TestCommand extends Command {

    @Override
    public void execute(CommandSender sender, String[] arguments) throws CommandException {
        // Print a chat message indicating the success of the
        mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(
                String.format("%s executed the \"Test\" command with arguments %s", sender.getName(), Arrays.toString(arguments))
        ));
    }
}
