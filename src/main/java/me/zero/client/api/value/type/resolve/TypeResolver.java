package me.zero.client.api.value.type.resolve;

import me.zero.client.api.value.Value;

import java.lang.reflect.Field;
import java.util.function.BiFunction;

/**
 * Takes in a field and its parent object and
 * attempts to convert it to a usable value object
 *
 * @author Brady
 * @since 2/21/2017 12:00 PM
 */
public interface TypeResolver<T extends Value> extends BiFunction<Object, Field, T> {}
