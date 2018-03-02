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
