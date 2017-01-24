package me.zero.client.api;

/**
 * The base for all Clients
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public abstract class Client {

    /**
     * Info of the Client
     */
    private ClientInfo info;

    public Client() {}

    /**
     * Called right after the Client API loads.
     * Used for Class Transformers and Module Type creation.
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

    /**
     * Sets the info, only works if the
     * current info is null
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
}
