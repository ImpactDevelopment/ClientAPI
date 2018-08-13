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

package clientapi.util.entity;

import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code EntityCheck} implementation that can have multiple child entity checks.
 *
 * @author Brady
 * @since 11/7/2017
 */
public final class EntityFilter implements EntityCheck {

    /**
     * The child {@code EntityFilters} that are run when checking the validity of an {@code Entity}
     */
    private final List<EntityCheck> checks = new ArrayList<>();

    public EntityFilter() {}

    public EntityFilter(EntityCheck... checks) {
        this(Arrays.asList(checks));
    }

    public EntityFilter(Iterable<EntityCheck> checks) {
        checks.forEach(this.checks::add);
    }

    @Override
    public final boolean isValid(Entity entity) {
        boolean containsAllow = this.checks.stream().anyMatch(filter -> filter.getType() == CheckType.ALLOW);

        return entity != null
                && this.checks.stream().filter(filter -> filter.getType() == CheckType.RESTRICT).allMatch(filter -> filter.isValid(entity))
                && (!containsAllow || this.checks.stream().filter(filter -> filter.getType() == CheckType.ALLOW).anyMatch(filter -> filter.isValid(entity)));

    }

    @Override
    public final CheckType getType() {
        return CheckType.RESTRICT;
    }

    /**
     * Adds the specified check to this {@code EntityFilter}
     * if it is not already a child filter.
     *
     * @param filter The filter to add
     */
    public final void addFilter(EntityCheck filter) {
        if (!this.checks.contains(filter))
            this.checks.add(filter);
    }

    /**
     * Removes the specified check from this {@code EntityFilter}
     * if it is already a child filter.
     *
     * @param filter The filter to add
     */
    public final void removeFilter(EntityCheck filter) {
        if (this.checks.contains(filter))
            this.checks.remove(filter);
    }

    /**
     * @return A copy of this {@code EntityFilter's} child checks.
     */
    public final List<EntityCheck> getChecks() {
        return new ArrayList<>(this.checks);
    }

    /**
     * Filters the specified collection of entities with all of this
     * {@code EntityFilter's} checks, and returns the filtered list.
     *
     * @param entities The collection to be filtered
     * @return A filtered list
     */
    public final List<Entity> filter(Collection<Entity> entities) {
        return entities.stream().filter(this::isValid).collect(Collectors.toList());
    }
}
