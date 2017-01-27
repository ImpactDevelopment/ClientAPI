package me.zero.client.api.module.plugin;

import me.zero.client.api.module.Module;

import java.util.ArrayList;
import java.util.List;

/**
 * Base for Plugin Main classes
 *
 * @since 1.0
 *
 * Created by Brady on 1/27/2017.
 */
public abstract class Plugin {

    private List<Module> modules = new ArrayList<>();
    private PluginInfo info;

    public abstract void onInit();

    public void setInfo(PluginInfo info) {
        if (this.info != null) return;
        this.info = info;
    }

    public PluginInfo getInfo() {
        return this.info;
    }

    void loadModule(Module module) {
        this.modules.add(module);
    }

    public List<Module> getModules() {
        return new ArrayList<>(modules);
    }
}
