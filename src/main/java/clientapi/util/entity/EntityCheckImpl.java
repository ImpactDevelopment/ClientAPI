package clientapi.util.entity;

import net.minecraft.entity.Entity;

import java.util.function.Function;

/**
 * Implementation of {@code EntityCheck}.
 *
 * @see EntityCheck
 * @see CheckType
 *
 * @author Brady
 * @since 2/5/2018 2:24 PM
 */
public final class EntityCheckImpl implements EntityCheck {

    /**
     * A function that tests the specified entity
     */
    private final Function<Entity, Boolean> isValid;

    /**
     * The type of check
     */
    private final CheckType type;

    public EntityCheckImpl(CheckType type, Function<Entity, Boolean> isValid) {
        this.type = type;
        this.isValid = isValid;
    }

    @Override
    public final boolean isValid(Entity entity) {
        return this.isValid.apply(entity);
    }

    @Override
    public final CheckType getType() {
        return this.type;
    }
}
