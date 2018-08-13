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

package me.zero.example;

import clientapi.Client;
import clientapi.command.Command;
import clientapi.config.ClientConfiguration;
import clientapi.manage.Manager;
import clientapi.module.Module;
import me.zero.example.command.ExampleCommandManager;
import me.zero.example.mod.ExampleModManager;

/**
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public final class ExampleClient extends Client {

    private static ExampleClient instance;
    private Manager<Module> moduleManager;
    private Manager<Command> commandManager;

    public ExampleClient(ClientConfiguration config) {
        super(config);
        instance = this;
    }

    @Override
    public final void init() {
        System.out.println(mc.mcDataDir);

        // Init and load module manager
        moduleManager = new ExampleModManager();
        moduleManager.load();

        // Init and load command manager
        commandManager = new ExampleCommandManager();
        commandManager.load();
    }

    public final String getName() {
        return this.config.getName();
    }

    public final String getVersion() {
        return this.config.getVersion();
    }

    public final Manager<Module> getModuleManager() {
        return this.moduleManager;
    }

    public final Manager<Command> getCommandManager() {
        return this.commandManager;
    }

    public static ExampleClient getInstance() {
        return instance;
    }
}
