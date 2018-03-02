/*
 * Copyright 2018 ImpactDevelopment
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
import clientapi.command.executor.ExecutionContext;
import clientapi.util.interfaces.Describable;
import clientapi.util.interfaces.Helper;

/**
 * Base for {@code Command}
 *
 * @see Command
 *
 * @author Brady
 * @since 5/30/2017 11:38 AM
 */
public interface ICommand extends Describable, Helper {

    /**
     * Executes this command from the specified sender with
     * the specified arguments, represented as a {@code String} array
     *
     * @param context The context behind command execution
     * @param arguments The arguments that
     */
    void execute(ExecutionContext context, String[] arguments) throws CommandException;

    /**
     * Returns the array of possible command "headers"
     * that can be used to execute this command. At least
     * one must be supplied, if not, then a
     *
     * @return The list of possible command headers
     */
    String[] getHeaders();
}
