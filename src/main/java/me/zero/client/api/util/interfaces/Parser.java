package me.zero.client.api.util.interfaces;

/**
 * Parses one object to another
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public interface Parser<F, T> extends Helper {

    /**
     * Parses from a type to another type
     *
     * @since 1.0
     *
     * @param obj Original type
     * @return New type
     */
    T parse(F obj);
}
