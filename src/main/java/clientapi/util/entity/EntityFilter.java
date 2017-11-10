package clientapi.util.entity;

import net.minecraft.entity.Entity;

/**
 * {@code FunctionalInterface} to determine the validity
 * of an entity based on this filter's standards.
 *
 * @author Brady
 * @since 11/7/2017 12:15 PM
 */
@FunctionalInterface
public interface EntityFilter {

    /**=
     * @param entity The entity to validate
     * @return Whether or not the specified entity is valid to this filter's standards.
     */
    boolean isValid(Entity entity);
}
