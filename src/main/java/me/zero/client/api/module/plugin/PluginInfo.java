package me.zero.client.api.module.plugin;

/**
 * Info for Plugins
 *
 * @since 1.0
 *
 * Created by Brady on 1/26/2017.
 */
class PluginInfo {

    private String name;
    private String description;
    private String main;

    public PluginInfo(String name, String description, String main) {
        this.main = main;
        this.description = description;
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMain() {
        return main;
    }
}
