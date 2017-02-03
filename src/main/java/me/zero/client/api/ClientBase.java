package me.zero.client.api;

import me.zero.client.api.manage.Manager;
import me.zero.client.api.module.Module;
import me.zero.client.load.ClientAPI;

import static me.zero.client.load.ClientAPI.Stage.PRE;

/**
 * @since 1.0
 *
 * Created by Brady on 1/25/2017.
 */
class ClientBase {

    /**
     * Info of the Client
     */
    private ClientInfo info;

    /**
     * The Module Manager
     */
    private Manager<Module> moduleManager;

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

    /**
     * Sets the Module Manager, it will
     * only be set if the moduleManager is null.
     *
     * @since 1.0
     *
     * @param moduleManager The Module Manager
     */
    protected void setModuleManager(Manager<Module> moduleManager) {
        ClientAPI.getAPI().check(PRE, "Manager Initialization after Pre-Initialization");

        if (this.moduleManager != null) return;
        this.moduleManager = moduleManager;
    }

    /**
     * @since 1.0
     *
     * @return The Module Manager
     */
    public Manager<Module> getModuleManager() {
        return this.moduleManager;
    }

    /**
     * Returns the module manager casted to the specified type
     *
     * @param impl The class of the implementation
     *
     * @since 1.0
     *
     * @return The Module Manager casted to this Client's implementation
     */
    @SuppressWarnings("unchecked")
    public <T extends Manager<Module>> T getModuleManager(Class<T> impl) {
        return (T) this.moduleManager;
    }
}
