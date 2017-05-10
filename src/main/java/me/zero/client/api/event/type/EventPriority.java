package me.zero.client.api.event.type;

/**
 * Contains all of the Event Priorities.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 1/21/2017 12:00PM
 */
public interface EventPriority {

    byte HIGHEST = 1, HIGH = 2, MEDIUM = 3, LOW = 4, LOWEST = 5, DEFAULT = MEDIUM;
}
