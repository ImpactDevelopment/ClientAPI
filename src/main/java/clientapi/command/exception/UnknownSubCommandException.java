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
 * Thrown by implementations of Command if the provided
 * arguments aren't valid. Hence, invalid synax.
 *
 * @author Brady
 * @since 5/31/2017
 */
public final class UnknownSubCommandException extends CommandException {

    /**
     * The arguments provided for execution
     */
    private final String[] arguments;

    public UnknownSubCommandException(Command command, String[] arguments) {
        super(command, "Unknown sub-command specified by given arguments");
        this.arguments = arguments;
    }

    /**
     * @return The arguments provided for execution
     */
    public final String[] getArguments() {
        return this.arguments;
    }
}
