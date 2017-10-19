/*
 * Copyright 2017 ZeroMemes
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
import clientapi.command.Sub;
import clientapi.command.executor.ExecutionContext;
import clientapi.util.builder.impl.ChatBuilder;
import net.minecraft.util.text.TextFormatting;

import java.util.Arrays;

/**
 * @author Brady
 * @since 5/31/2017 6:18 PM
 */
@Cmd(headers = { "test", "example"}, description = "Test Command")
public final class TestCommand extends Command {

    /**
     * Sub command with no defining header, that takes
     * in any arguments that are passed to the command,
     * indicated by the vararg.
     *
     * @param context Context behind command execution
     * @param arguments Arguments
     */
    @Sub
    private void handle(ExecutionContext context, String... arguments) {
        // Print a chat message indicating the success of the
        mc.ingameGUI.getChatGUI().printChatMessage(
                new ChatBuilder()
                        .append(String.format("%s executed the ", context.sender().getName()), TextFormatting.GRAY)
                        .append("\"Test\"", TextFormatting.WHITE)
                        .append(" command with arguments ", TextFormatting.GRAY)
                        .append(Arrays.toString(arguments), TextFormatting.WHITE)
                        .build());
    }
}
