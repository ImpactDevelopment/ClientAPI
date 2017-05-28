package me.zero.client.api.event.defaults;

import me.zero.event.type.Cancellable;
import net.minecraft.entity.Entity;

/**
 * Called before Render#renderLivingLabel(Entity, String, double, double. double, int).
 * The event is cancellable, if cancelled then the
 * rendering of the label will be cancelled.
 *
 * @author Brady
 * @since 5/23/2017 5:40 PM
 */
public final class RenderEntityLabelEvent extends Cancellable {

    /**
     * The entity that is having their label rendered
     */
    private final Entity entity;

    /**
     * The text being rendered on the label
     */
    private final String text;

    public RenderEntityLabelEvent(Entity entity, String text) {
        this.entity = entity;
        this.text = text;
    }

    /**
     * The entity having their name rendered
     */
    public final Entity getEntity() {
        return this.entity;
    }

    /**
     * @return The text being rendered on the label
     */
    public final String getText() {
        return this.text;
    }
}
