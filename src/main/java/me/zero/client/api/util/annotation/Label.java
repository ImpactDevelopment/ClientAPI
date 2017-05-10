package me.zero.client.api.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Basic label for fields. Gives them a name,
 * description, id and a list of aliases.
 *
 * @author Brady
 * @since 2/11/2017 12:00 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Label {

    /**
     * @return The name
     */
    String name();

    /**
     * @return The ID of the field
     */
    String id();

    /**
     * @return The description
     */
    String description();

    /**
     * @return The array of aliases
     */
    String[] aliases() default {};
}
