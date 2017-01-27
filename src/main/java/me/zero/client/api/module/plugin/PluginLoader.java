package me.zero.client.api.module.plugin;

import com.google.gson.GsonBuilder;
import javassist.bytecode.Descriptor;
import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import me.zero.client.api.util.Messages;
import me.zero.client.api.util.logger.Level;
import me.zero.client.api.util.logger.Logger;
import me.zero.client.load.ClientAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Used to Load plugins
 *
 * @since 1.0
 *
 * Created by Brady on 1/26/2017.
 */
public class PluginLoader {

    private List<Plugin> plugins = new ArrayList<>();

    private String pluginDir;

    public PluginLoader(String pluginDir) {
        ClientAPI.getAPI().check(ClientAPI.Stage.INIT, "Plugin Loading after Initialization");
        this.pluginDir = pluginDir;
    }

    private void loadPlugins() {
        List<File> possiblePlugins = new ArrayList<>();

        File dir = new File(this.pluginDir);

        if (!dir.isDirectory()) return;

        for (File file : dir.listFiles()) {
            if (file.getAbsolutePath().endsWith(".jar")) {
                possiblePlugins.add(file);
            }
        }

        for (File file : possiblePlugins) {
            try {
                loadPlugin(file, new JarFile(file));
                Logger.instance.logf(Level.INFO, Messages.PLUGIN_LOAD, file.getAbsolutePath());
            } catch (IOException e) {
                Logger.instance.logf(Level.WARNING, Messages.PLUGIN_JARFILE_CREATE, e);
            }
        }
    }

    private void loadPlugin(File file, JarFile jarFile) {
        JarEntry pJson = jarFile.getJarEntry("plugin.json");

        if (pJson == null)
            return;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(jarFile.getInputStream(pJson)));
            PluginInfo info = new GsonBuilder().setPrettyPrinting().create().fromJson(reader, PluginInfo.class);

            if (info != null) {
                ClassLoader classLoader = URLClassLoader.newInstance(new URL[] { file.toURI().toURL() });

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
                            Class clazz = Class.forName(Descriptor.toJavaName(name.substring(0, name.length() - 6)), true, classLoader);

                            if (clazz != null && clazz.getSuperclass().equals(Module.class) && clazz.isAnnotationPresent(Mod.class)) {
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

    public List<Plugin> getPlugins() {
        return this.plugins;
    }
}
