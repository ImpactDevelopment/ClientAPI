package me.zero.client.api.util;

/**
 * Basic callback, useful in networking responses
 *
 * @since 1.0
 *
 * Created by Brady on 2/25/2017.
 */
public interface Callback<P> {

    void call(P param);
}
