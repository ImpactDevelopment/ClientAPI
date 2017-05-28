package me.zero.client.api.event.defaults;

import me.zero.event.type.Cancellable;
import net.minecraft.entity.Entity;

/**
 * Called when the team color is retrieved when
 * rendering outlines. This is particularly useful
 * when making a Spectral ESP or Shader ESP and
 * there is reliability on the renderOutlines flag.
 *
 * To apply the color set by this event, the event
 * must be cancelled. {@code Cancellable#cancel}
 *
 * @author Brady
 * @since 5/21/2017 11:37 AM
 */
public final class TeamColorEvent extends Cancellable {

    /**
     * Entity being colored
     */
    private final Entity entity;

    /**
     * The color of the entity.
     */
    private int color = 0xFFFFFFFF;

    public TeamColorEvent(Entity entity) {
        this.entity = entity;
    }

    /**
     * @return The entity being colored
     */
    public final Entity getEntity() {
        return this.entity;
    }

    /**
     * @return The new color for the entity
     */
    public final int getColor() {
        return this.color;
    }

    /**
     * Sets the color of the entity
     *
     * @param color New color
     */
    public final void setColor(int color) {
        this.color = color;
    }
}
