package clientapi.event.task;

import clientapi.util.interfaces.Identifiable;

/**
 * @author Brady
 * @since 11/29/2017 2:46 PM
 */
public interface Task extends Identifiable {

    /**
     * Returns whether or not this {@code Task} is active. Active
     * tasks indicate that the execution of the task will be conflict-free.
     * Tasks may only be run once at a time from the same manager.
     *
     * @return Whether or not this {@code Task} is active.
     */
    boolean isActive();

    /**
     * Sets the active state of this {@code Task}.
     *
     * @see Task#isActive()
     *
     * @param active The new active state of this task.
     */
    void setActive(boolean active);

    /**
     * @see TaskPriority
     *
     * @return The execution priority of this task.
     */
    byte getPriority();
}
