package me.zero.client.api.value.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks any annotations that are used to mark
 * fields as values as valid value annotations.
 *
 * @author Brady
 * @since 5/22/2017 5:06 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ValueDefinition {}
