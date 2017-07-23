/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.event.defaults.game.render;

import me.zero.alpine.type.Cancellable;
import me.zero.alpine.type.EventState;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

/**
 * Called before and after an entity is rendered
 *
 * @author Brady
 * @since 3/2/2017 12:00 PM
 */
public final class EntityRenderEvent extends Cancellable {

    /**
     * The state of this Event
     */
    private final EventState state;

    /**
     * The renderer used
     */
    private final Render<?> renderer;

    /**
     * The entity being rendered
     */
    private final Entity entity;

    /**
     * Coordinates of the entity being rendered
     */
    private final double x, y, z;

    /**
     * The yaw facing angle of the entity being rendered
     */
    private final float entityYaw;

    /**
     * Current render partial-ticks
     */
    private final float partialTicks;

    public EntityRenderEvent(EventState state, Render<?> renderer, Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.state = state;
        this.renderer = renderer;

        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.entityYaw = entityYaw;
        this.partialTicks = partialTicks;
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
    public final Render<?> getRenderer() {
        return this.renderer;
    }

    /**
     * @return The entity being rendered
     */
    public final Entity getEntity() {
        return this.entity;
    }

    /**
     * @return The X coordinate of the entity being rendered
     */
    public final double getX() {
        return this.x;
    }

    /**
     * @return The Y coordinate of the entity being rendered
     */
    public final double getY() {
        return this.y;
    }

    /**
     * @return The Z coordinate of the entity being rendered
     */
    public final double getZ() {
        return this.z;
    }

    /**
     * @return The yaw facing angle of the entity being rendered
     */
    public final float getEntityYaw() {
        return this.entityYaw;
    }

    /**
     * @return The current render partial ticks
     */
    public final float getPartialTicks() {
        return this.partialTicks;
    }
}
