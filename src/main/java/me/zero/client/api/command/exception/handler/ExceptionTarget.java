package me.zero.client.api.command.exception.handler;

import me.zero.client.api.command.exception.CommandException;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to provide {@code ExceptionHandler} implementations
 * with their exception target.
 *
 * @see ExceptionHandler
 *
 * @author Brady
 * @since 6/1/2017 2:55 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ExceptionTarget {

    Class<? extends CommandException> value();
}
