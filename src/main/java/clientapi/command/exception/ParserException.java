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
import clientapi.command.executor.argument.ArgumentParser;

import java.lang.reflect.Type;

/**
 * @author Brady
 * @since 10/18/2017 2:50 PM
 */
public final class ParserException extends CommandException {

    /**
     * The child command involved in the exception
     */
    private final ChildCommand child;

    /**
     * The parser involved
     */
    private final ArgumentParser<?> parser;

    /**
     * The raw data that was passed to the parser
     */
    private final String raw;

    /**
     * The type that was expected to be returned
     */
    private final Type expected;

    /**
     * The type that the parser returned
     */
    private final Class<?> returned;

    public ParserException(Command command, ChildCommand child, ArgumentParser<?> parser, String raw, Type expected, Class<?> returned) {
        super(command, "Parser return type was unexpected");
        this.child = child;
        this.parser = parser;
        this.raw = raw;
        this.expected = expected;
        this.returned = returned;
    }

    /**
     * @return The child command involved in the exception
     */
    public final ChildCommand getChildCommand() {
        return this.child;
    }

    /**
     * @return The parser involved
     */
    public final ArgumentParser<?> getParser() {
        return this.parser;
    }

    /**
     * @return The raw data that was passed to the parser
     */
    public final String getRaw() {
        return this.raw;
    }

    /**
     * @return The type that was expected to be returned
     */
    public final Type getExpected() {
        return this.expected;
    }

    /**
     * @return The type that the parser returned
     */
    public final Class<?> getReturned() {
        return this.returned;
    }
}
