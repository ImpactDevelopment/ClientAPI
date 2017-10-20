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

import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import clientapi.Client;
import clientapi.ClientAPI;
import clientapi.ClientInfo;
import clientapi.command.Command;
import clientapi.event.defaults.game.render.TextEvent;
import clientapi.event.handle.ClientHandler;
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

        ClientAPI.EVENT_BUS.subscribe(new Object() {
            @EventHandler
            private final Listener<TextEvent> textEventListener = new Listener<>(event -> {
                if (event.getText().contains("Singleplayer")) {
                    event.setText(event.getText().replace("Singleplayer", "Singleplayer"));
                }
            });
        });
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
