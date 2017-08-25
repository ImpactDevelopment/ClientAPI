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

package clientapi.command;

import clientapi.command.exception.CommandException;
import clientapi.command.executor.sender.CommandSender;
import clientapi.util.interfaces.Helper;

/**
 * Base for Command
 *
 * @see Command
 * @author Brady
 * @since 5/30/2017 11:38 AM
 */
interface ICommand extends Helper {

    /**
     * Executes this command from the specified sender with the specified
     * arguments, represented as a {@code String} array
     *
     * @param sender The Sender that executed this command
     * @param arguments The arguments that
     */
    void execute(CommandSender sender, String[] arguments)
        throws CommandException;

    /**
     * Returns the array of possible command "headers" that can be used to
     * execute this command. At least one must be supplied, if not, then a
     *
     * @return The list of possible command headers
     */
    String[] headers();

    /**
     * Returns the intended purpose of the command, a brief description of the
     * usage.
     *
     * @return The description of the command usage.
     */
    String description();

    /**
     * Returns the syntax of the command. The syntax gives instructions on how
     * the command may be executed. Commands without arguments can provide an
     * empty array.
     *
     * @return The syntax of the command.
     */
    String[] syntax();
}
