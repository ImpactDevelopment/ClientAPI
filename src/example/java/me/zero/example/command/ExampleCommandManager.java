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

package me.zero.example.command;

import clientapi.ClientAPI;
import clientapi.command.Command;
import clientapi.command.handler.CommandHandler;
import clientapi.command.handler.listener.ChatCommandListener;
import clientapi.manage.Manager;
import me.zero.example.command.commands.TestCommand;

/**
 * Created by Brady on 2/16/2017.
 */
public final class ExampleCommandManager extends Manager<Command> {

    private final CommandHandler handler = new CommandHandler(this);

    public ExampleCommandManager() {
        super("Command");
    }

    @Override
    public void load() {
        this.addData(
                new TestCommand()
        );

        // Setup the handler and a chat command listener
        ClientAPI.EVENT_BUS.subscribe(
                handler,
                new ChatCommandListener(handler)
        );
    }

    @Override
    public void save() {}
}
