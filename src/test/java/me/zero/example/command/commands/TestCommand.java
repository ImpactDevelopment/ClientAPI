/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.example.command.commands;

import clientapi.command.Cmd;
import clientapi.command.Command;
import clientapi.command.exception.CommandException;
import clientapi.command.executor.sender.CommandSender;

import net.minecraft.util.text.TextComponentString;

import java.util.Arrays;

/**
 * @author Brady
 * @since 5/31/2017 6:18 PM
 */
@Cmd(headers = {"test", "example"}, description = "Test Command")
public final class TestCommand extends Command {

    @Override
    public void execute(CommandSender sender, String[] arguments)
        throws CommandException {
        // Print a chat message indicating the success of the
        mc.ingameGUI.getChatGUI()
            .printChatMessage(new TextComponentString(String.format(
                "%s executed the \"Test\" command with arguments %s",
                sender.getName(), Arrays.toString(arguments))));
    }
}
