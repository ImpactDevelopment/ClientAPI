package me.zero.example.command.commands;

import clientapi.command.Cmd;
import clientapi.command.Command;
import clientapi.command.exception.CommandException;
import clientapi.command.executor.sender.CommandSender;
import clientapi.util.builder.impl.ChatBuilder;
import net.minecraft.util.text.TextFormatting;

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
        mc.ingameGUI.getChatGUI().printChatMessage(
                new ChatBuilder()
                        .append(String.format("%s executed the ", sender.getName()), TextFormatting.GRAY)
                        .append("\"Test\"", TextFormatting.WHITE)
                        .append(" command with arguments ", TextFormatting.GRAY)
                        .append(Arrays.toString(arguments), TextFormatting.WHITE)
                        .build());
    }
}
