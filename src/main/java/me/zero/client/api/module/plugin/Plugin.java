/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.module.plugin;

import me.zero.client.api.module.Module;

import java.util.ArrayList;
import java.util.List;

/**
 * Base for Plugin Main classes. Plugins are loaded with
 * the {@code PluginLoader}
 *
 * @author Brady
 * @since 1/27/2017 12:00 PM
 */
public abstract class Plugin {

    /**
     * The list of Modules associated with this Plugin
     */
    private final List<Module> modules = new ArrayList<>();

    /**
     * The info for this plugin
     */
    private PluginInfo info;

    /**
     * Plugin Init method, Not used for anything essential.
     * Just a way for plugin developers to know
     * when the Client {@code onInit(ClientInfo)} has finished.
     */
    public abstract void onInit();

    /**
     * Sets the info for this plugin, the info
     * will only be set if it has not already
     * been set.
     *
     * @param info The info being set
     */
    final void setInfo(PluginInfo info) {
        if (this.info != null) return;
        this.info = info;
    }

    /**
     * @return The Info of this plugin
     */
    public final PluginInfo getInfo() {
        return this.info;
    }

    /**
     * Loads a Module into the Module list of this plugin
     *
     * @param module Module being loaded
     */
    final void loadModule(Module module) {
        this.modules.add(module);
    }

    /**
     * @return The module list associated with this plugin
     */
    public final List<Module> getModules() {
        return new ArrayList<>(modules);
    }
}
