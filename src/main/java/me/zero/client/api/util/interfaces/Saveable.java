package me.zero.client.api.util.interfaces;

/**
 * Simple interface to tag classes for being Saveable
 *
 * @see me.zero.client.api.manage.Manager
 * @see Loadable
 *
 * @author Brady
 * @since 1/19/2017 12:00 PM
 */
public interface Saveable {

    /**
     * Save function
     */
    void save();
}
