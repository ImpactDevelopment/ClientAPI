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

import clientapi.command.exception.*;
import clientapi.command.executor.ExecutionContext;
import clientapi.command.executor.argument.ArgumentParser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Brady
 * @since 2/15/2018 1:47 PM
 */
public final class ChildCommand implements ICommand {

    private final String[] headers;
    private final String description;
    private final List<CommandArgument> arguments;
    private final Command parent;
    private final Method handle;

    ChildCommand(Command parent, Method handle) {
        Sub sub = handle.getAnnotation(Sub.class);

        this.headers = sub.headers();
        this.description = sub.description();
        this.arguments = new ArrayList<>();
        this.parent = parent;
        this.handle = handle;

        this.setupArguments(sub);
    }

    @Override
    public final void execute(ExecutionContext context, String[] arguments) throws CommandException {
        int expectedArgs = this.arguments.size();

        // Check if the handle has an optional argument
        boolean hasOptional = expectedArgs > 0 && this.arguments.get(this.arguments.size() - 1).isOptional();

        // Only accept the argument count if there are the same acmount of specified arguemnts
        // as the expected amount, or, if there is an optional argument, one less than the specified
        // amount.
        if (!(arguments.length == expectedArgs || (hasOptional && arguments.length == expectedArgs - 1))) {
            throw new InvalidSyntaxException(this.parent, this, arguments.length, expectedArgs);
        }

        // List to hold all arguments to be passed to the handle method
        List<Object> callArguments = new ArrayList<>();
        callArguments.add(context);

        for (int i = 0; i < expectedArgs; i++) {
            boolean isMissingOptional = hasOptional && arguments.length != expectedArgs && i == expectedArgs - 1;

            Type type = this.arguments.get(i).getType();

            ArgumentParser<?> parser = context.handler().getParser(type);
            if (parser == null) {
                throw new InvalidParserException(this.parent, this, arguments[i], type);
            }

            // Parse the string
            Object parsed = parser.parse(context, type, isMissingOptional ? "" : arguments[i]);
            if (parsed == null) {
                throw new InvalidArgumentException(this.parent, this, arguments, i, type);
            }

            // Ensure that the return type is valid
            if (parser.isTarget(parsed.getClass())) {
                callArguments.add(parsed);
            } else {
                throw new ParserException(this.parent, this, parser, isMissingOptional ? "Optional (none provided)" : arguments[i], type, parsed.getClass());
            }
        }

        try {
            // Invoke the sub-command handle method
            boolean accessible = handle.isAccessible();
            handle.setAccessible(true);
            handle.invoke(this.parent, callArguments.toArray(new Object[0]));
            handle.setAccessible(accessible);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final String[] getHeaders() {
        return this.headers;
    }

    @Override
    public final String getDescription() {
        return this.description;
    }

    /**
     * @return The list of argument for this {code ChildCommand}
     */
    public final List<CommandArgument> getArguments() {
        return this.arguments;
    }

    /**
     * @return The parent of this {@code ChildCommand}
     */
    public final Command getParent() {
        return this.parent;
    }

    /**
     * Finds and creates the arguments for this {@code ChildCommand}.
     *
     * @param sub The {@code @Sub} annotation on the handle method
     */
    private void setupArguments(Sub sub) {
        // Find argument names
        String[] arguments = sub.arguments();

        // If the arguments specified by @Sub do not match the expected
        // arguments length, then get the names of the method's parameters.
        int expected = handle.getParameterCount() - 1;
        if (arguments.length != expected) {
            arguments = new String[expected];
            for (int i = 0; i < expected; i++) {
                arguments[i] = handle.getParameters()[i + 1].getName();
            }
        }

        // Create arguments
        for (int i = 1; i < handle.getGenericParameterTypes().length; i++) {
            Type type = handle.getGenericParameterTypes()[i];
            this.arguments.add(new CommandArgument(arguments[i - 1], type));
        }
    }
}
