package me.zero.client.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Brady
 * @since 5/31/2017 8:55 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cmd {

    /**
     * Provides the command class with the headers
     *
     * @see ICommand#headers()
     *
     * @return Command headers
     */
    String[] headers();

    /**
     * Provides the command class with the description
     *
     * @see ICommand#description()
     *
     * @return Command description
     */
    String description();

    /**
     * Provides the command class with the syntax
     *
     * @see ICommand#syntax()
     *
     * @return Command syntax
     */
    String[] syntax() default {};
}
