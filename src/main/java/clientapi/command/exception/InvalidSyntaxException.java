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

import clientapi.command.ChildCommand;
import clientapi.command.Command;

/**
 * @author Brady
 * @since 10/18/2017 11:07 AM
 */
public final class InvalidSyntaxException extends CommandException {

    /**
     * The child command involved in the exception
     */
    private final ChildCommand child;

    public InvalidSyntaxException(Command command, ChildCommand child, int found, int expected) {
        super(command, found > expected ? "Too many arguments provided" : found < expected ? "Not enough arguments provided" : "Correct amount of arguments provided, unknown cause.");
        this.child = child;
    }

    /**
     * @return The child command involved in the exception
     */
    public final ChildCommand getChildCommand() {
        return this.child;
    }
}
