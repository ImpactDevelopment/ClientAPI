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

package clientapi.event.defaults.internal;

import clientapi.command.Command;
import clientapi.command.executor.ExecutionContext;

/**
 * @author Brady
 * @since 6/11/2017 9:42 AM
 */
public final class CommandExecutionEvent {

    /**
     * The command being executed
     */
    private final Command command;

    /**
     * Context behind the command's execution
     */
    private final ExecutionContext context;

    /**
     * Arguments to be passed to the command
     */
    private final String[] arguments;

    public CommandExecutionEvent(Command command, ExecutionContext context, String[] arguments) {
        this.command = command;
        this.context = context;
        this.arguments = arguments;
    }

    public final Command getCommand() {
        return this.command;
    }

    public final ExecutionContext getContext() {
        return this.context;
    }

    public final String[] getArguments() {
        return this.arguments;
    }
}
