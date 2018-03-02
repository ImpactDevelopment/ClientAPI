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
import clientapi.command.exception.CommandInitException;
import clientapi.command.exception.UnknownSubCommandException;
import clientapi.command.executor.ExecutionContext;
import clientapi.util.ClientAPIUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@code ICommand}
 *
 * @author Brady
 * @since 5/31/2017 8:55 AM
 */
public class Command implements ICommand {

    /**
     * A list of this command's child commands
     */
    private final List<ChildCommand> children = new ArrayList<>();

    /**
     * A list of headers used to execute this command
     */
    private String[] headers;

    /**
     * A description of this command's usage
     */
    private String description;

    public Command() {
        if (!this.getClass().isAnnotationPresent(Cmd.class))
            throw new RuntimeException(new CommandInitException(this, "@Cmd annotation must be present if required parameters aren't passed through constructor"));

        Cmd data = this.getClass().getAnnotation(Cmd.class);
        setup(data.headers(), data.description());
    }

    public Command(String[] headers, String description) {
        setup(headers, description);
    }

    private void setup(String[] headers, String description) {
        this.headers = headers;
        this.description = description;

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
            children.add(new ChildCommand(this, method));
        }

        if (ClientAPIUtils.containsNull(headers, description))
            throw new NullPointerException("One or more Command members were null!");
    }

    @Override
    public final void execute(ExecutionContext context, String[] arguments) throws CommandException {
        ChildCommand sub = findChild(arguments);
        if (sub == null)
            throw new UnknownSubCommandException(this, arguments);

        // If the child was found by it's header, then remove the first argument.
        if (sub.getHeaders().length > 0 && arguments.length > 0) {
            String[] newArgs = new String[arguments.length - 1];
            System.arraycopy(arguments, 1, newArgs, 0, arguments.length - 1);
            arguments = newArgs;
        }

        sub.execute(context, arguments);
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
     * @return A list of this command's child commands.
     */
    public final List<ChildCommand> getChildren() {
        return this.children;
    }

    /**
     * Finds a child command from the specified arguments
     *
     * @param arguments The arguments
     * @return The child command, {@code null} if not found
     */
    private ChildCommand findChild(String[] arguments) {
        String header = arguments.length == 0 ? null : arguments[0];

        // Find the command by the header defined by the first argument
        ChildCommand child = this.children.stream().filter(c -> (header == null && c.getHeaders().length == 0) || ClientAPIUtils.containsIgnoreCase(c.getHeaders(), header)).findFirst().orElse(null);
        if (child != null) {
            return child;
        }

        // Find the command by the length of the arguments
        child = this.children.stream().filter(c -> c.getHeaders().length == 0 && arguments.length == c.getArguments().size()).findFirst().orElse(null);
        if (child != null) {
            return child;
        }

        return null;
    }
}
