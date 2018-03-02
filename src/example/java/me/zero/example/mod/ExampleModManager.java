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

package me.zero.example.mod;

import clientapi.ClientAPI;
import clientapi.manage.Manager;
import clientapi.module.Module;
import me.zero.example.mod.mods.*;
import org.apache.logging.log4j.Level;

/**
 * @author Brady
 * @since 1/25/2017 12:00 PM
 */
public final class ExampleModManager extends Manager<Module> {

    public ExampleModManager() {
        super("Module");
    }

    @Override
    public final void load() {
        ClientAPI.LOGGER.log(Level.INFO, "Loading Modules");

        // Load Modules
        this.addAll(
            new Aura(),
            new Camera(),
            new Fly(),
            new Hud(),
            new Speed()
        );
    }

    @Override
    public final void save() {
        // Save Modules
    }
}
