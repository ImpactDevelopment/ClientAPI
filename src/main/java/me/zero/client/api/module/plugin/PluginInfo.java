package me.zero.client.api.module.plugin;

/**
 * Info for Plugins
 *
 * @since 1.0
 *
 * Created by Brady on 1/26/2017.
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
     * @since 1.0
     *
     * @return The name of this plugin
     */
    public String getName() {
        return name;
    }

    /**
     * @since 1.0
     *
     * @return The description of this plugin
     */
    public String getDescription() {
        return description;
    }

    /**
     * @since 1.0
     *
     * @return The main class of this plugin
     */
    public String getMain() {
        return main;
    }
}
