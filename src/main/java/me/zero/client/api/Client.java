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

package me.zero.client.api;

import me.zero.client.api.event.handle.ClientHandler;
import me.zero.client.api.module.plugin.Plugin;
import me.zero.client.api.module.plugin.PluginLoader;
import me.zero.client.api.util.interfaces.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * The base for all ClientAPI Clients. The classpath of implementations
 * of {@code Client} should be defined in the {@code client.json} file.
 *
 * @see ClientInfo
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public abstract class Client implements Helper {

    /**
     * The ClientInfo created from the discovered {@code client.json}
     */
    protected final ClientInfo info;

    /**
     * The list of plugin loaders that have been used by this client
     */
    private final List<PluginLoader> pluginLoaders = new ArrayList<>();

    public Client(ClientInfo info) {
        this.info = info;
    }

    /**
     * Called after the game has initialized.
     *
     * @see me.zero.client.api.module.Module
     * @see me.zero.client.api.module.plugin.Plugin
     *
     * @param handler The handler that's being used to handle Client API internal events
     */
    public abstract void onInit(ClientHandler handler);

    /**
     * @return The Client Info
     */
    public ClientInfo getInfo() {
        return this.info;
    }

    /**
     * Creates and registers a PluginLoader from the specified directory
     *
     * @return The created PluginLoader
     */
    protected PluginLoader loadPlugins(String path) {
        PluginLoader loader = new PluginLoader(path);
        pluginLoaders.add(loader);
        return loader;
    }

    /**
     * Returns all of the PluginLoaders. Usually used
     * for loading the Modules from the Plugins
     *
     * @return The list of Plugin Loaders
     */
    public List<PluginLoader> getPluginLoaders() {
        return new ArrayList<>(this.pluginLoaders);
    }

    /**
     * Returns all of the Plugins from all of the
     * PluginLoaders. Cleaner way of getting a list
     * of all of the Plugins.
     *
     * @return The list of Plugins
     */
    public List<Plugin> getPlugins() {
        List<Plugin> plugins = new ArrayList<>();
        this.pluginLoaders.forEach(loader -> plugins.addAll(loader.getPlugins()));
        return plugins;
    }
}
