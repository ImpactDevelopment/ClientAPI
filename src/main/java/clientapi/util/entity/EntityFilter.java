package clientapi.util.entity;

import net.minecraft.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * {@code EntityFilter} implementation that can have multiple child entity filters.
 *
 * @author Brady
 * @since 11/7/2017 12:14 PM
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
        List<EntityCheck> allowChecks = this.checks.stream().filter(filter -> filter.getType() == CheckType.ALLOW).collect(Collectors.toList());

        return entity != null
                && this.checks.stream().filter(filter -> filter.getType() == CheckType.RESTRICT).allMatch(filter -> filter.isValid(entity))
                && (allowChecks.size() == 0 || allowChecks.stream().anyMatch(filter -> filter.isValid(entity)));

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
