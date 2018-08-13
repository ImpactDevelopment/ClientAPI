/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.event.task;

/**
 * @author Brady
 * @since 11/30/2017
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
