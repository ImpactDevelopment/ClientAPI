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

package clientapi.util.entity;

import net.minecraft.entity.Entity;

/**
 * {@code FunctionalInterface} to determine the validity
 * of an entity based on this filter's standards.
 *
 * @author Brady
 * @since 11/7/2017 12:15 PM
 */
public interface EntityCheck {

    /**
     * @param entity The entity to validate
     * @return Whether or not the specified entity is valid to this filter's standards
     */
    boolean isValid(Entity entity);

    /**
     * @return The type of this filter
     */
    CheckType getType();
}
