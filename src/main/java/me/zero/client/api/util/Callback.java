package me.zero.client.api.util;

/**
 * Basic callback, useful in networking responses
 *
 * @author Brady
 * @since 2/25/2017 12:00 PM
 */
public interface Callback<P> {

    void call(P param);
}
