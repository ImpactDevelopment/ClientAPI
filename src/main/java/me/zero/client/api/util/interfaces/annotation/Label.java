package me.zero.client.api.util.interfaces.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Basic label for fields. Gives them a name,
 * description, id and a list of aliases.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/11/2017 12:00 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Label {

    /**
     * @since 1.0
     *
     * @return The name
     */
    String name();

    /**
     * @since 1.0
     *
     * @return The ID of the field
     */
    String id();

    /**
     * @since 1.0
     *
     * @return The description
     */
    String description();

    /**
     * @since 1.0
     *
     * @return The array of aliases
     */
    String[] aliases() default {};
}
