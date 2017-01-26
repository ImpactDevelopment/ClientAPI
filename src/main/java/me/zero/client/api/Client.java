package me.zero.client.api;

/**
 * The base for all Clients
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public abstract class Client extends ClientBase {

    /**
     * Called right after the Client API loads.
     * Used for Class Transformers, Module Type
     * creation, and Manager initialization.
     *
     * @see #onInit()
     * @see #postInit()
     * @see me.zero.client.api.module.ModuleType
     *
     * @since 1.0
     */
    public abstract void preInit();

    /**
     * Called after Class Transformers are run
     * Used for Module Registry
     *
     * @see #preInit()
     * @see #postInit()
     * @see me.zero.client.api.module.Module
     *
     * @since 1.0
     */
    public abstract void onInit();

    /**
     * Last init call once everything else is done
     * Used for GUI things such as Fonts and Click/Tab GUIs
     *
     * @see #preInit()
     * @see #onInit()
     * @see me.zero.client.api.gui.tab.ITabGui
     *
     * @since 1.0
     */
    public abstract void postInit();
}
