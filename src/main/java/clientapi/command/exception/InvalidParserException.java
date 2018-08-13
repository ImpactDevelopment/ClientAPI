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
 * Thrown when there isn't a parser for an expected argument type.
 *
 * @author Brady
 * @since 1/4/2018
 */
public final class InvalidParserException extends CommandException {

    /**
     * The child command involved in the exception
     */
    private final ChildCommand child;

    /**
     * The argument that was given for the parameter
     * with a type that doesn't have a defined parser.
     */
    private final String argument;

    /**
     * The {@code Type} that doesn't have a defined parser.
     */
    private final Type type;

    public InvalidParserException(Command command, ChildCommand child, String argument, Type type) {
        super(command);
        this.child = child;
        this.argument = argument;
        this.type = type;
    }

    /**
     * @return The child command involved in the exception
     */
    public final ChildCommand getChildCommand() {
        return this.child;
    }

    /**
     * @return The argument given for the invalid command parameter.
     */
    public final String getArgument() {
        return this.argument;
    }

    /**
     * @return The {@code Type} that doesn't have a defined parser.
     */
    public final Type getType() {
        return this.type;
    }
}
