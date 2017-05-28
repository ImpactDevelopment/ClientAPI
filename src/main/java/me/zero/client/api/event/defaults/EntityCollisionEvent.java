package me.zero.client.api.event.defaults;

import me.zero.event.type.Cancellable;
import net.minecraft.entity.Entity;

/**
 * Called when 2 entities collide with one another.
 * If cancelled, the collision doesn't occur.
 *
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
public final class EntityCollisionEvent extends Cancellable {

    /**
     * Entity being collided into
     */
    private final Entity entity;

    /**
     * Entity colliding into other entity
     */
    private final Entity collidingEntity;

    public EntityCollisionEvent(Entity entity, Entity collidingEntity) {
        this.entity = entity;
        this.collidingEntity = collidingEntity;
    }

    /**
     * @return The entity being collided into
     */
    public final Entity getEntity() {
        return this.entity;
    }

    /**
     * @return Entity colliding into other entity
     */
    public final Entity getCollidingEntity() {
        return this.collidingEntity;
    }
}
