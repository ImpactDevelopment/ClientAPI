package me.zero.client.api.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Category annotation to mark interfaces as
 * categories. This annotation isn't placed
 * anywhere in the API, only in Clients based
 * on it.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/6/2017 12:00PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Category {

    /**
     * @since 1.0
     *
     * @return The name of the Category
     */
    String name();

    /**
     * Default Category
     */
    @Category(name = "Default") class Default {}
}
