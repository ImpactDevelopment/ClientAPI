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
import clientapi.util.ClientAPIUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of {@code ICommand}
 *
 * @author Brady
 * @since 5/31/2017 8:55 AM
 */
public class Command implements ICommand {

    private final Command parent;
    private final List<Command> children = new ArrayList<>();
    private final Method handle;
    private String[] headers;
    private String[] arguments;
    private String description;

    public Command() {
        if (!this.getClass().isAnnotationPresent(Cmd.class))
            throw new RuntimeException(new CommandInitException(this, "@Cmd annotation must be present if required parameters aren't passed through constructor"));

        this.parent = null;
        this.handle = null;
        Cmd data = this.getClass().getAnnotation(Cmd.class);
        setup(data.headers(), data.description());
    }

    public Command(String[] headers, String description) {
        this.parent = null;
        this.handle = null;
        setup(headers, description);
    }

    private Command(String[] headers, String description, Command parent, Method handle) {
        if (ClientAPIUtils.containsNull(parent, handle))
            throw new RuntimeException(new CommandInitException(this, "Parent and Handle must be non-null"));

        this.parent = parent;
        this.handle = handle;
        setup(headers, description);
    }

    private void setup(String[] headers, String description) {
        this.headers = headers;
        this.description = description;

        if (this.parent == null) {
            for (Method method : this.getClass().getDeclaredMethods()) {
                int parameters = method.getParameterCount();
                if (parameters == 0 || !method.getParameterTypes()[0].equals(ExecutionContext.class) || !method.isAnnotationPresent(Sub.class))
                    continue;

                // Ensure that there is only one optional at most, and that it is the last parameter
                int optionals = 0;
                for (int i = 0; i < parameters; i++) {
                    if (method.getParameterTypes()[i].isAssignableFrom(Optional.class)) {
                        if (i != parameters - 1) {
                            throw new RuntimeException(new CommandInitException(this, "Optionals must be defined as the last parameter"));
                        }
                        if (++optionals > 1) {
                            throw new RuntimeException(new CommandInitException(this, "More than one optional parameter is not supported"));
                        }
                    }
                }

                // Create the child command
                Sub sub = method.getAnnotation(Sub.class);
                Command subc = new Command(sub.headers(), sub.description(), this, method);

                // Setup argument names
                String[] arguments = sub.arguments();
                if (arguments.length != parameters - 1) {
                    arguments = new String[parameters - 1];
                    for (int i = 1; i < parameters; i++) {
                        arguments[i - 1] = method.getParameters()[i].getName();
                    }
                }
                subc.arguments = arguments;

                children.add(subc);
            }

            this.arguments = this.children.stream()
                    .map(child -> child.arguments.length > 0 ? child.arguments[0] : "")
                    .collect(Collectors.toList())
                    .toArray(new String[0]);
        }

        if (ClientAPIUtils.containsNull(headers, description))
            throw new NullPointerException("One or more Command members were null!");
    }

    @Override
    public final void execute(ExecutionContext context, String[] arguments) throws CommandException {
        if (this.parent == null) {
            Command sub = findChild(arguments.length == 0 ? null : arguments[1]);
            if (sub == null)
                throw new InvalidSyntaxException(this);

            sub.execute(context, arguments);
        } else {
            int params = handle.getParameterCount();

            // Subtract 1 because the first parameter is the context
            int expectedArgs = params - 1;

            // Check if the handle has an optional argument
            boolean hasOptional = params > 1 && Optional.class.isAssignableFrom(handle.getParameterTypes()[params - 1]);

            // Only accept the argument count if there are the same acmount of specified arguemnts
            // as the expected amount, or, if there is an optional argument, one less than the specified
            // amount.
            if (!(arguments.length == expectedArgs || (hasOptional && arguments.length == expectedArgs - 1))) {
                throw new ArgumentCountException(this, arguments.length, expectedArgs);
            }

            // List to hold all arguments to be passed to the handle method
            List<Object> callArguments = new ArrayList<>();
            callArguments.add(context);

            for (int i = 0; i < expectedArgs; i++) {
                boolean isMissingOptional = hasOptional && arguments.length != expectedArgs && i == expectedArgs - 1;

                // Add 1 because the first parameter is the context
                Class<?> type = handle.getParameterTypes()[i + 1];

                // Get the parser for the parameter type
                ArgumentParser<?> parser = context.handler().getParser(type);
                if (parser == null) {
                    throw new InvalidArgumentException(this, arguments, i, type);
                }

                // Parse the string
                Object parsed = parser.parse(context, type, isMissingOptional ? "" : arguments[i]);
                if (parsed == null) {
                    throw new InvalidArgumentException(this, arguments, i, type);
                }

                // Ensure that the return type is valid
                if (parser.isTarget(parsed.getClass())) {
                    callArguments.add(parsed);
                } else {
                    throw new ParserException(this, parser, isMissingOptional ? "Optional<?>" : arguments[i], type, parsed.getClass());
                }
            }

            try {
                // Invoke the sub-command handle method
                handle.invoke(this, callArguments.toArray(new Object[0]));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Finds a child command with the given header
     *
     * @param header The header of a possible child command
     * @return The child command, {@code null} if not found
     */
    private Command findChild(String header) {
        return this.children.stream().filter(child -> (header == null && child.headers.length == 0) || ClientAPIUtils.containsIgnoreCase(child.headers, header)).findFirst().orElse(null);
    }

    @Override
    public final String[] headers() {
        return this.headers;
    }

    @Override
    public String[] arguments() {
        return this.arguments;
    }

    @Override
    public final String description() {
        return this.description;
    }

    @Override
    public ICommand parent() {
        return this.parent;
    }
}
