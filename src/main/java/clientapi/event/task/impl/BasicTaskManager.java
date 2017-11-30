package clientapi.event.task.impl;

import clientapi.event.task.Task;
import clientapi.event.task.TaskManager;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The most basic implementation of {@code TaskManager}.
 *
 * @author Brady
 * @since 11/30/2017 2:31 PM
 */
public class BasicTaskManager implements TaskManager {

    /**
     * The overflow task queue
     *
     * @see TaskManager#startOrQueue(Task)
     */
    private final Queue<Task> taskQueue = new LinkedBlockingQueue<>();

    /**
     * The current active task
     *
     * @see TaskManager#getCurrentTask()
     */
    private Task currentTask;

    @Override
    public final boolean start(Task task) {
        if (currentTask == null) {
            (currentTask = task).setActive(true);
            return true;
        } else if (currentTask.getPriority() < task.getPriority()) {
            currentTask.setActive(false);
            (currentTask = task).setActive(true);
            return true;
        }
        return false;
    }

    @Override
    public final boolean startOrQueue(Task task) {
        boolean canStart = start(task);
        if (!canStart) {
            taskQueue.add(task);
        }
        return canStart;
    }

    @Override
    public final boolean finish() {
        if (currentTask != null) {
            currentTask.setActive(false);
            currentTask = null;

            Task nextTask = taskQueue.poll();
            if (nextTask != null) {
                this.start(nextTask);
            }
        }
        return false;
    }

    @Override
    public final Task getCurrentTask() {
        return this.currentTask;
    }
}
