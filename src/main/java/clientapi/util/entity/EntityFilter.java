package clientapi.util.entity;

import net.minecraft.entity.Entity;

/**
 * @author Brady
 * @since 11/7/2017 12:15 PM
 */
@FunctionalInterface
public interface EntityFilter {

    boolean isValid(Entity entity);
}
