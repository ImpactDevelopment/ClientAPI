package me.zero.client.api.manage;

/**
 * Simple interface to tag classes for being Loadable
 *
 * @see me.zero.client.api.manage.Manager
 * @see me.zero.client.api.manage.Saveable
 *
 * @since 1.0
 *
 * Created by Brady on 1/19/2017.
 */
public interface Loadable {

    void save();
}
