package me.zero.client.api.util.interfaces;

/**
 * Simple interface to tag classes for being Loadable
 *
 * @see me.zero.client.api.manage.Manager
 * @see Saveable
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public interface Loadable {

    /**
     * Load function
     */
    void load();
}
