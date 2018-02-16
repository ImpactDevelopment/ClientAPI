/*
 * Copyright 2017 ImpactDevelopment
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
