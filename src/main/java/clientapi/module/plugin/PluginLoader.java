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

package clientapi.module.plugin;

import clientapi.module.Module;
import clientapi.util.Messages;
import clientapi.util.logger.Level;
import clientapi.util.logger.Logger;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Used to Load plugins
 *
 * @author Brady
 * @since 1/26/2017 12:00 PM
 */
public final class PluginLoader {

    /**
     * The list of the Plugins discovered
     */
    private final List<Plugin> plugins = new ArrayList<>();

    /**
     * Directory containing possible plugins
     */
    private final String pluginDir;

    public PluginLoader(String pluginDir) {
        this.pluginDir = pluginDir;
        this.loadPlugins();
    }

    /**
     * Loads plugins
     */
    private void loadPlugins() {
        File dir = new File(this.pluginDir);
        File[] files = dir.listFiles();
        if (files == null)
            return;

        Arrays.stream(files).forEach(file -> {
            if (file.getAbsolutePath().endsWith(".jar")) {
                loadPlugin(file);
                Logger.instance.logf(Level.INFO, Messages.PLUGIN_LOAD, file.getAbsolutePath());
            }
        });
    }

    /**
     * Loads a single plugin from the file
     *
     * @param file The file of the plugin
     */
    private void loadPlugin(File file) {
        JarFile jarFile;

        try {
            jarFile = new JarFile(file);
        } catch (IOException e) {
            Logger.instance.logf(Level.WARNING, Messages.PLUGIN_JARFILE_CREATE, e);
            return;
        }

        JarEntry pJson = jarFile.getJarEntry("plugin.json");

        if (pJson == null)
            return;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(pJson)));
            PluginInfo info = new GsonBuilder().setPrettyPrinting().create().fromJson(reader, PluginInfo.class);

            if (info != null) {
                ClassLoader classLoader = new URLClassLoader(new URL[] { file.toURI().toURL() }, this.getClass().getClassLoader());

                Plugin plugin;

                try {
                    plugin = (Plugin) classLoader.loadClass(info.getMain()).newInstance();
                    plugin.setInfo(info);
                    plugins.add(plugin);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    Logger.instance.logf(Level.WARNING, Messages.PLUGIN_INSTANTIATION, e);
                    return;
                }

                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry e = entries.nextElement();
                    String name = e.getName();

                    if (name.endsWith(".class")) {
                        try {
                            Class clazz = Class.forName(name.substring(0, name.length() - 6).replace('/', '.'), true, classLoader);

                            if (clazz != null && clazz.getSuperclass().equals(Module.class)) {
                                try {
                                    plugin.loadModule((Module) clazz.newInstance());
                                } catch (InstantiationException | IllegalAccessException exception) {
                                    Logger.instance.logf(Level.WARNING, Messages.PLUGIN_CANT_CREATE_MODULE, exception);
                                }
                            }
                        } catch (ClassNotFoundException exception) {
                            Logger.instance.logf(Level.WARNING, Messages.PLUGIN_CANT_LOAD_CLASS, name);
                        }
                    }
                }
            }
        } catch (IOException e) {
            Logger.instance.logf(Level.WARNING, Messages.PLUGIN_CANT_CREATE_INPUTSTREAM, file.getAbsolutePath());
        }
    }

    /**
     * @return The list of plugins that were discovered
     */
    public final List<Plugin> getPlugins() {
        return this.plugins;
    }
}
