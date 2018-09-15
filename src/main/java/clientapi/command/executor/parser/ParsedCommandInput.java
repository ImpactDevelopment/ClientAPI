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

package clientapi.command.executor.parser;

/**
 * Used to hold parsed input data. Contains the name of the
 * targetted command and the arguments for execution as raw text.
 *
 * @author Brady
 * @since 4/14/2018
 */
public final class ParsedCommandInput {

    /**
     * The specified command header
     */
    private final String command;

    /**
     * The execution arguments expressed as raw text
     */
    private final String[] arguments;

    ParsedCommandInput(String command, String[] arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    /**
     * @return The specified command header
     */
    public final String getCommand() {
        return this.command;
    }

    /**
     * @return The execution arguments expressed as raw text
     */
    public final String[] getArguments() {
        return this.arguments;
    }
}
