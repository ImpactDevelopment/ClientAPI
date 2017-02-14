package me.zero.client.api.command.parse;

import me.zero.client.api.util.interfaces.Parser;

/**
 * Parses a generic type from a string
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public interface ArgumentParser<T> extends Parser<String, T> {

    /**
     * Parses the generic type from a string
     *
     * @since 1.0
     *
     * @param t The type as a string
     * @return The actual type
     */
    T parse(String t);
}
