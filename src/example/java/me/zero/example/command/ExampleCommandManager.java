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

package me.zero.example.command;

import clientapi.ClientAPI;
import clientapi.command.Command;
import clientapi.command.executor.parser.impl.*;
import clientapi.command.handler.CommandHandler;
import clientapi.command.handler.listener.ChatCommandListener;
import clientapi.manage.Manager;
import me.zero.example.ExampleClient;
import me.zero.example.command.commands.TestCommand;

/**
 * @author Brady
 * @since 2/16/2017 12:00 PM
 */
public final class ExampleCommandManager extends Manager<Command> {

    private final CommandHandler handler = new CommandHandler(this);

    public ExampleCommandManager() {
        super("Command");
    }

    @Override
    public final void load() {
        this.addAll(
                new TestCommand()
        );

        handler.registerParser(new BlockParser());
        handler.registerParser(new BooleanParser());
        handler.registerParser(new CharParser());
        handler.registerParser(new ColorParser());
        handler.registerParser(new CommandParser(ExampleClient.getInstance().getCommandManager()));
        handler.registerParser(new ModuleParser(ExampleClient.getInstance().getModuleManager()));
        handler.registerParser(new NumberParser());
        handler.registerParser(new OptionalParser());
        handler.registerParser(new StringParser());

        // Create a new chat command listener so chat commands can be passed through chat
        ClientAPI.EVENT_BUS.subscribe(new ChatCommandListener(handler));
    }

    @Override
    public final void save() {

    }
}
