package clientapi.util.entity;

import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Brady
 * @since 11/7/2017 12:14 PM
 */
public final class MultiEntityFilter implements EntityFilter {

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
        return this.filters.stream().filter(filter -> !filter.isValid(entity)).count() > 0;
    }

    public final List<Entity> filter(Collection<Entity> entities) {
        return entities.stream().filter(this::isValid).collect(Collectors.toList());
    }
}
