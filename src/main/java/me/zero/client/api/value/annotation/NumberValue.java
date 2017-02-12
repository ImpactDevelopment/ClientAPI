package me.zero.client.api.value.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark fields as Number Values
 *
 * @since 1.0
 *
 * Created by Brady on 1/23/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NumberValue {

    double min();

    double max();
}
