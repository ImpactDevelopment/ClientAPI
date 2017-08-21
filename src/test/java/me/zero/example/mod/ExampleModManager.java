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

package me.zero.example.mod;

import clientapi.manage.Manager;
import clientapi.module.Module;
import clientapi.util.logger.Level;
import clientapi.util.logger.Logger;

import me.zero.example.ExampleClient;
import me.zero.example.mod.mods.*;

/**
 * Created by Brady on 1/25/2017.
 */
public final class ExampleModManager extends Manager<Module> {

	public ExampleModManager() {
		super("Module");
	}

	@Override
	public void load() {
		Logger.instance.log(Level.INFO, "Loading Modules");

		// Load Modules
		this.addData(new Aura(), new Camera(), new Fly(), new Hud(),
		    new Speed());

		// Loads Modules from the discovered Plugins by the Plugin loaders
		ExampleClient.getInstance().getPlugins()
		    .forEach(plugin -> this.addData(plugin.getModules()));
	}

	@Override
	public void save() {
		// Save Modules
	}
}
