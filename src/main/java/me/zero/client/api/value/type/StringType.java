package me.zero.client.api.value.type;

import me.zero.client.api.value.Value;

import java.lang.reflect.Field;

/**
 * Created by Brady on 1/23/2017.
 */
public class StringType extends Value<String> {

    public StringType(String name, Object object, Field field) {
        super(name, object, field);
    }
}
