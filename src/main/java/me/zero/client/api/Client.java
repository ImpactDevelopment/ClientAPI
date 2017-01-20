package me.zero.client.api;

/**
 * Created by Brady on 1/19/2017.
 */
public abstract class Client {

    /**
     * Called right after the Client API loads.
     * Used for Class Transformers and Module Type creation.
     *
     * @see me.zero.client.api.module.ModuleType
     */
    public abstract void preInit();

    /**
     * Called after Class Transformers are run
     * Used for Module Registry
     *
     * @see me.zero.client.api.module.Module
     */
    public abstract void onInit();

    /**
     * Last init call once everything else is done
     */
    public abstract void postInit();
}
