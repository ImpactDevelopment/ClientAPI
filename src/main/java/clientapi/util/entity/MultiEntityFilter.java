package clientapi.util.entity;

import net.minecraft.entity.Entity;

import java.util.*;
import java.util.stream.Collectors;

/**
 * {@code EntityFilter} implementation that can have multiple child entity filters.
 *
 * @author Brady
 * @since 11/7/2017 12:14 PM
 */
public final class MultiEntityFilter implements EntityFilter {

    /**
     * The child {@code EntityFilters} that are run when checking the validity of an {@code Entity}
     */
    private final List<EntityFilter> filters = new ArrayList<>();

    public MultiEntityFilter() {}

    public MultiEntityFilter(EntityFilter... filters) {
        this(Arrays.asList(filters));
    }

    public MultiEntityFilter(Iterable<EntityFilter> filters) {
        filters.forEach(this.filters::add);
    }

    @Override
    public final boolean isValid(Entity entity) {
        return entity != null && this.filters.stream().filter(filter -> !filter.isValid(entity)).count() == 0;

    }

    /**
     * Adds the specified filter to this {@code MultiEntityFilter}
     * if it is not already a child filter.
     *
     * @param filter The filter to add
     */
    public final void addFilter(EntityFilter filter) {
        if (!this.filters.contains(filter))
            this.filters.add(filter);
    }

    /**
     * Removes the specified filter from this {@code MultiEntityFilter}
     * if it is already a child filter.
     *
     * @param filter The filter to add
     */
    public final void removeFilter(EntityFilter filter) {
        if (this.filters.contains(filter))
            this.filters.remove(filter);
    }

    /**
     * @return A copy of this {@code MultiEntityFilter's} child filters.
     */
    public final List<EntityFilter> getFilters() {
        return new ArrayList<>(this.filters);
    }

    /**
     * Filters the specified collection of entities with all of this
     * {@code MultiEntityFilter's} child filters, and returns the filtered list.
     *
     * @param entities The collection to be filtered
     * @return A filtered list
     */
    public final List<Entity> filter(Collection<Entity> entities) {
        return entities.stream().filter(this::isValid).collect(Collectors.toList());
    }
}
