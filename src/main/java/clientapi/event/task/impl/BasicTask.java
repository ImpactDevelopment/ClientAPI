package clientapi.event.task.impl;

import clientapi.event.task.Task;
import clientapi.event.task.TaskPriority;
import clientapi.util.interfaces.Identifiable;

/**
 * The most basic implementation of {@code Task}.
 *
 * @author Brady
 * @since 11/30/2017 2:58 PM
 */
public class BasicTask implements Task {

    /**
     * The priority of this task
     *
     * @see Task#getPriority()
     */
    private final byte priority;

    /**
     * The identifier of this task
     *
     * @see Identifiable#getID()
     */
    private String id;

    /**
     * Whether or not this task is active
     *
     * @see Task#isActive()
     */
    private boolean active;

    public BasicTask(String id) {
        this(id, TaskPriority.DEFAULT);
    }

    public BasicTask(String id, byte priority) {
        this.priority = priority;
        this.id = id;
    }

    @Override
    public boolean isActive() {
        return this.active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public byte getPriority() {
        return this.priority;
    }

    @Override
    public String getID() {
        return this.id;
    }
}
