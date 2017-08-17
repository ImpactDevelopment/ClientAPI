/*
 * Copyright 2017 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.event.defaults.game.render;

import me.zero.alpine.type.Cancellable;
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
