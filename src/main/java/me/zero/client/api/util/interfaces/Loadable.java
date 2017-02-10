package me.zero.client.api.util.interfaces;

/**
 * Simple interface to tag classes for being Loadable
 *
 * @see me.zero.client.api.manage.Manager
 * @see Saveable
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public interface Loadable {

    /**
     * Load function
     *
     * @since 1.0
     */
    void load();
}
