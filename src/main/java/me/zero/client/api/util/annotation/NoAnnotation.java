package me.zero.client.api.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to bypass class instantiation that requires
 * an annotation to be present
 *
 * @author Brady
 * @since 5/10/2017 4:23 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NoAnnotation {
}
