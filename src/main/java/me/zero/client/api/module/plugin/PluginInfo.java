package me.zero.client.api.module.plugin;

/**
 * Info for Plugins
 *
 * @author Brady
 * @since 1/26/2017 12:00 PM
 */
class PluginInfo {

    /**
     * Name of the Plugin
     */
    private String name;

    /**
     * Description of the Plugin
     */
    private String description;

    /**
     * Main class of the Plugin
     */
    private String main;

    /**
     * @return The name of this plugin
     */
    public String getName() {
        return name;
    }

    /**
     * @return The description of this plugin
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return The main class of this plugin
     */
    public String getMain() {
        return main;
    }
}
