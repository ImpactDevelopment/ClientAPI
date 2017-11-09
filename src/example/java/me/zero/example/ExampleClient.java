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

package me.zero.example;

import clientapi.lua.LuaHandler;
import clientapi.lua.LuaScript;
import clientapi.util.io.StreamReader;
import clientapi.Client;
import clientapi.ClientInfo;
import clientapi.command.Command;
import clientapi.manage.Manager;
import clientapi.module.Module;
import me.zero.example.command.ExampleCommandManager;
import me.zero.example.mod.ExampleModManager;

import javax.script.ScriptException;

/**
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public final class ExampleClient extends Client {

    private static ExampleClient instance;
    private Manager<Module> moduleManager;
    private Manager<Command> commandManager;

    public ExampleClient(ClientInfo info) {
        super(info);
        instance = this;
    }

    @Override
    public void onInit(ClientInfo info) {
        // Init and load module manager
        moduleManager = new ExampleModManager();
        moduleManager.load();

        // Init and load command manager
        commandManager = new ExampleCommandManager();
        commandManager.load();

        // Setup example script
        String scriptSource = new StreamReader(ExampleClient.class.getResourceAsStream("/lua/example.lua")).all();
        LuaScript script = LuaHandler.getHandler().createScript(LuaScript.Type.HOOK, scriptSource);
        try {
            script.compile();
            script.eval();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    public final String getName() {
        return this.info.getName();
    }

    public final double getVersion() {
        return this.info.getBuild();
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
