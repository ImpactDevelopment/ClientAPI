package me.zero.client.api.value.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark fields as Multi Values
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/24/2017 12:00PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MultiValue {

    String[] value();
}
