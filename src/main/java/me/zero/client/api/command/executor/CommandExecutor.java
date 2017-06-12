/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.command.executor;

import me.zero.client.api.command.Command;
import me.zero.client.api.command.exception.CommandException;
import me.zero.client.api.command.executor.sender.CommandSender;

/**
 * Takes in a command, and executes it from the specified
 * sender and arguments.
 *
 * @see DirectExecutor
 *
 * @author Brady
 * @since 6/11/2017 9:26 AM
 */
public interface CommandExecutor {

    void execute(Command command, CommandSender sender, String[] arguments) throws CommandException;
}
