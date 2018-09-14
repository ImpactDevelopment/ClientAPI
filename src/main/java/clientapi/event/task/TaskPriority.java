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
 * A duplicate of {@link EventPriority}. Used to define the execution
 * priority of tasks. Tasks with higher priorities will overpower tasks
 * of lower priorities when they are being started.
 *
 * @author Brady
 * @since 11/30/2017
 */
public interface TaskPriority {

    byte HIGHEST = 1, HIGH = 2, MEDIUM = 3, LOW = 4, LOWEST = 5, DEFAULT = MEDIUM;
}

