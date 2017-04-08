package me.zero.client.api;

import me.zero.client.api.command.Command;
import me.zero.client.api.exception.NotInitializedException;
import me.zero.client.api.manage.Manager;
import me.zero.client.api.module.Category;
import me.zero.client.api.module.Module;
import me.zero.client.api.module.plugin.Plugin;
import me.zero.client.api.module.plugin.PluginLoader;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;
import java.util.List;

/**
 * Base for Client file, only reason why this
 * is separate is for the cleanlieness of the
 * code.
 *
 * @since 1.0
 *
 * Created by Brady on 1/25/2017.
 */
class ClientBase implements Helper {

    /**
     * Info of the Client
     */
    private ClientInfo info;

    /**
     * The Message Prefix
     */
    private String prefix;

    /**
     * The Module Manager
     */
    private Manager<Module> moduleManager;

    /**
     * The Command Manager
     */
    private Manager<Command> commandManager;

    /**
     * The list of plugin loaders that have been used by this client
     */
    private List<PluginLoader> pluginLoaders = new ArrayList<>();

    /**
     * Sets the info, only works if the current info is null
     *
     * @since 1.0
     *
     * @param info Info being set
     */
    public void setInfo(ClientInfo info) {
        if (this.info != null) return;
        this.info = info;
    }

    /**
     * @since 1.0
     *
     * @return The Client Info
     */
    public ClientInfo getInfo() {
        return this.info;
    }

    /**
     * Sets the prefix, only set if it hasn't been set yet
     *
     * @since 1.0
     *
     * @param prefix The prefix being set
     */
    public void setPrefix(String prefix) {
        if (this.prefix != null) return;
        this.prefix = prefix;
    }

    /**
     * @since 1.0
     *
     * @return The prefix
     */
    public String getPrefix() {
        return this.prefix;
    }

    /**
     * Sets the Module Manager, it will
     * only be set if the moduleManager is null.
     *
     * @since 1.0
     *
     * @param moduleManager The Module Manager
     */
    protected void setModuleManager(Manager<Module> moduleManager) {
        if (this.moduleManager != null) return;
        this.moduleManager = moduleManager;
    }

    /**
     * Returns the module manager casted to the specified type
     *
     * @since 1.0
     *
     * @return The Module Manager casted to this Client's implementation
     */
    @SuppressWarnings("unchecked")
    public <T extends Manager<Module>> T getModuleManager() {
        if (this.moduleManager == null)
            throw new NotInitializedException("Module Manager has not yet been initialized");

        return (T) this.moduleManager;
    }

    /**
     * Sets the Command Manager, it will
     * only be set if the commandManager is null.
     *
     * @since 1.0
     *
     * @param commandManager The Module Manager
     */
    protected void setCommandManager(Manager<Command> commandManager) {
        if (this.commandManager != null) return;
        this.commandManager = commandManager;
    }

    /**
     * Returns the command manager casted to the specified type
     *
     * @since 1.0
     *
     * @return The Command Manager casted to this Client's implementation
     */
    @SuppressWarnings("unchecked")
    public <T extends Manager<Command>> T getCommandManager() {
        if (this.commandManager == null)
            throw new NotInitializedException("Command Manager has not yet been initialized");

        return (T) this.commandManager;
    }

    /**
     * Creates and registers a PluginLoader from the specified directory
     *
     * @since 1.0
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
     * @since 1.0
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
     * @since 1.0
     *
     * @return The list of Plugins
     */
    public List<Plugin> getPlugins() {
        List<Plugin> plugins = new ArrayList<>();
        this.pluginLoaders.forEach(loader -> plugins.addAll(loader.getPlugins()));
        return plugins;
    }

    /**
     * Prints a chat message
     *
     * @since 1.0
     *
     * @param message The message
     */
    // TODO: Create a chat builder and change "message" to it
    public void printChatMessage(String message) {
        mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(prefix + " " + message));
    }

    /**
     * Gets the list of module types/categories
     * that exist.
     *
     * @since 1.0
     *
     * @return The categories
     */
    public List<Class<?>> getCategories() {
        return this.getCategories(false);
    }

    /**
     * Gets the list of module types/categories
     * that exist.
     *
     * @since 1.0
     *
     * @param sort Whether or not to sort alphabetically
     * @return The categories
     */
    public List<Class<?>> getCategories(boolean sort) {
        List<Class<?>> categories = new ArrayList<>();
        getModuleManager().getData().forEach(module -> {
            Class<?> category = module.getType();

            if (!categories.contains(category))
                categories.add(category);
        });
        if (sort) {
            categories.sort((c1, c2) -> {
                String n1 = c1.getAnnotation(Category.class).name();
                String n2 = c2.getAnnotation(Category.class).name();
                return String.CASE_INSENSITIVE_ORDER.compare(n1, n2);
            });
        }
        return categories;
    }
}
