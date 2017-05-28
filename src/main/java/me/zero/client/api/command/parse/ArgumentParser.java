package me.zero.client.api.command.parse;

import me.zero.client.api.util.interfaces.Helper;

import java.util.function.Function;

/**
 * Parses a generic type from a string
 *
 * @author Brady
 * @since 2/13/2017 12:00 PM
 */
@Deprecated
public interface ArgumentParser<T> extends Function<String, T>, Helper {}
