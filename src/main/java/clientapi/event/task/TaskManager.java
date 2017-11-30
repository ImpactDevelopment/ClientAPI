package clientapi.event.task;

/**
 * @author Brady
 * @since 11/30/2017 2:22 PM
 */
public interface TaskManager {

    /**
     * Attempts to start the specified task. The task can only
     * be started if there isn't a task currently running, or if
     * the current task running has a lower priority than the specified
     * task.
     *
     * @param task The task to start
     * @return Whether or not the task could be started
     */
    boolean start(Task task);

    /**
     * Attempts to start the specified task. The task can only be started
     * if there isn't a task currently running, or if the current task
     * running has a lower priority than the specified task. If the task
     * cannot be started, it is added to a queue, and when the current
     * task is finished by calling {@code TaskManager#finish}, the queued
     * task will be called. Queued tasks will not be called when a task is
     * being started that overpowers the priority level of the current task.
     *
     * @param task The task to start
     * @return Whether or not the task could be started
     */
    boolean startOrQueue(Task task);

    /**
     * Finishes the specified task. This is done by setting the
     * current task's active state to {@code false}, indicating
     * that it may no longer execute. Returns whether or not there
     * is an active task. If there are tasks waiting in the queue,
     * they will be started.
     *
     * @return Whether or not there is an active task
     */
    boolean finish();

    /**
     * @return The current task that is active in this manager, null if none.
     */
    Task getCurrentTask();
}
