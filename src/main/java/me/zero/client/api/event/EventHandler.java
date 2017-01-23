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
 * Created by Brady on 1/21/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {

    /**
     * @return The priority of the Event
     */
    byte value() default EventPriority.DEFAULT;
}
