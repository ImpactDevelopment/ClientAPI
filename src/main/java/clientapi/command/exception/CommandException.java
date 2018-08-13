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

package clientapi.command.exception;

import clientapi.command.Command;

/**
 * Superclass for all exceptions in the {@code clientapi.command.exception} package.
 *
 * @author Brady
 * @since 5/30/2017
 */
public class CommandException extends Exception {

    /**
     * Command that encountered an exception
     */
    private final Command command;

    public CommandException(Command command) {
        this.command = command;
    }

    public CommandException(Command command, String message) {
        super(message);
        this.command = command;
    }

    public CommandException(Command command, String message, Object... args) {
        super(String.format(message, args));
        this.command = command;
    }

    /**
     * @return The command that encountered an exception
     */
    public final Command getCommand() {
        return this.command;
    }
}
