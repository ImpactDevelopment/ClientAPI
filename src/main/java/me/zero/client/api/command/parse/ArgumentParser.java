package me.zero.client.api.command.parse;

import me.zero.client.api.util.interfaces.Helper;

import java.util.function.Function;

/**
 * Parses a generic type from a string
 *
 * @since 1.0
 *
 * Created by Brady on 2/13/2017.
 */
public interface ArgumentParser<T> extends Function<String, T>, Helper {}
