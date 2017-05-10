package me.zero.client.api.util.interfaces;

/**
 * Simple interface to tag classes for being Loadable
 *
 * @see me.zero.client.api.manage.Manager
 * @see Saveable
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/19/2017 12:00PM
 */
public interface Loadable {

    /**
     * Load function
     *
     * @since 1.0
     */
    void load();
}
