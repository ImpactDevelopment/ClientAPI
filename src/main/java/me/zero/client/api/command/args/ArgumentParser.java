package me.zero.client.api.command.args;

/**
 * Parses a generic type from a string
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public interface ArgumentParser<T> {

    T parse(String t);
}
