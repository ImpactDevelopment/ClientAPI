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

import java.lang.reflect.Type;

/**
 * Thrown when an argument that was passed to the command
 * was not the expected type. For example, a number was expected
 * but the number was unable to be parsed.
 *
 * @author Brady
 * @since 6/7/2017 9:34 AM
 */
public final class InvalidArgumentException extends CommandException {

    /**
     * The child command involved in the exception
     */
    private final ChildCommand child;

    /**
     * Array of inputted arguments
     */
    private final String[] args;

    /**
     * Index of the bag argument
     */
    private final int badArg;

    /**
     * Expected type
     */
    private final Type expected;

    public InvalidArgumentException(Command command, ChildCommand child, String[] args, int badArg, Type expected) {
        super(command);
        this.child = child;
        this.args = args;
        this.badArg = badArg;
        this.expected = expected;
    }

    /**
     * @return The child command involved in the exception
     */
    public final ChildCommand getChildCommand() {
        return this.child;
    }

    /**
     * @return Array of inputted arguments
     */
    public final String[] getArgs() {
        return this.args;
    }

    /**
     * @return Index of the bad argument
     */
    public final int getBadArg() {
        return this.badArg;
    }

    /**
     * Returns the expected class type of the given argument.
     *
     * @return The expected type
     */
    public Type getExpectedType() {
        return this.expected;
    }
}
