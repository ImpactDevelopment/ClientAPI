/*
 * Copyright 2017 ZeroMemes
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

package clientapi.event.defaults.game.entity;

import me.zero.alpine.type.Cancellable;
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
