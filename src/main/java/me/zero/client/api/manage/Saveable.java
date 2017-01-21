package me.zero.client.api.manage;

/**
 * Simple interface to tag classes for being Saveable
 *
 * @see me.zero.client.api.manage.Manager
 * @see me.zero.client.api.manage.Loadable
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public interface Saveable {

    /**
     * Save function
     *
     * @since 1.0
     */
    void save();
}
