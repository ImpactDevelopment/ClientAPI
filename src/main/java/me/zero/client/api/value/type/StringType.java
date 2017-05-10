package me.zero.client.api.value.type;

import me.zero.client.api.value.Value;

import java.lang.reflect.Field;

/**
 * Basic type for String values
 *
 * @see me.zero.client.api.value.annotation.StringValue
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/23/2017 12:00PM
 */
public final class StringType extends Value<String> {

    public StringType(String name, String id, String description, Object object, Field field) {
        super(name, id, description, object, field);
    }
}
