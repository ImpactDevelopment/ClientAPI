package clientapi.event.task;

/**
 * A duplicate of {@code EventPriority}. Used to define the execution
 * priority of tasks. Tasks with higher priorities will overpower tasks
 * of lower priorities when they are being started.
 *
 * @author Brady
 * @since 11/30/2017 2:50 PM
 */
public interface TaskPriority {

    byte HIGHEST = 1, HIGH = 2, MEDIUM = 3, LOW = 4, LOWEST = 5, DEFAULT = MEDIUM;
}

