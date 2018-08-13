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

package clientapi.command.executor.parser.impl;

import clientapi.command.Command;
import clientapi.command.executor.ExecutionContext;
import clientapi.command.executor.parser.ArgumentParser;
import clientapi.manage.Manager;

import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @author Brady
 * @since 11/3/2017
 */
public final class CommandParser implements ArgumentParser<Command> {

    private final Manager<Command> commandManager;

    public CommandParser(Manager<Command> commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public final Command parse(ExecutionContext context, Type type, String raw) {
        return commandManager.stream().filter(cmd -> Arrays.stream(cmd.getHeaders()).anyMatch(s -> s.equalsIgnoreCase(raw))).findFirst().orElse(null);
    }

    @Override
    public final boolean isTarget(Type type) {
        return type instanceof Class && Command.class.isAssignableFrom((Class) type);
    }
}
