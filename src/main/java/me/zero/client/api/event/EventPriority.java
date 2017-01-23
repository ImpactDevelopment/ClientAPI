package me.zero.client.api.event;

/**
 * Contains all of the Event Priorities.
 *
 * @since 1.0
 *
 * Created by Brady on 1/21/2017.
 */
public interface EventPriority {

    byte HIGHEST = 1, HIGH = 2, MEDIUM = 3, LOW = 4, LOWEST = 5, DEFAULT = MEDIUM;
}
