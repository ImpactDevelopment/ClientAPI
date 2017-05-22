package me.zero.client.api.value.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark fields as Number Values
 *
 * @author Brady
 * @since 1/23/2017 12:00 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ValueDefinition
public @interface NumberValue {

    double min();

    double max();
}
