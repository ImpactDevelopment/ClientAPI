package me.zero.client.api.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark Methods as Event Handlers
 *
 * @see me.zero.client.api.event.EventManager
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/21/2017 12:00 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EventHandler {}
