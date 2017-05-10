package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.EventState;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;

/**
 * Called before and after an entity is rendered
 *
 * @author Brady
 * @since 3/2/2017 12:00 PM
 */
public final class EntityRenderEvent {

    /**
     * The state of this Event
     */
    private final EventState state;

    /**
     * The renderer used
     */
    private final RenderLivingBase<?> renderer;

    /**
     * The entity being rendered
     */
    private final Entity entity;

    public EntityRenderEvent(EventState state, RenderLivingBase<?> renderer, Entity entity) {
        this.state = state;
        this.renderer = renderer;
        this.entity = entity;
    }

    /**
     * @return The event state
     */
    public final EventState getState() {
        return this.state;
    }


    /**
     * @return The renderer used
     */
    public final RenderLivingBase<?> getRenderer() {
        return this.renderer;
    }

    /**
     * @return The entity being rendered
     */
    public final Entity getEntity() {
        return this.entity;
    }
}
