/*
 * Copyright 2018 ImpactDevelopment
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
import me.zero.alpine.type.EventState;
import net.minecraft.entity.EntityLivingBase;

/**
 * Called before and after {@code EntityLivingBase#jump()} is
 * invoked. Should only be cancelled in the {@code PRE} state
 * to prevent the entity from jumping.
 *
 * @see EntityLivingBase#jump()
 *
 * @author Brady
 * @since 12/8/2017
 */
public final class EntityJumpEvent extends Cancellable {

    /**
     * The state of the event
     */
    private final EventState state;

    /**
     * The {@code EntityLivingBase} that is jumping
     */
    private final EntityLivingBase entity;

    public EntityJumpEvent(EventState state, EntityLivingBase entity) {
        this.state = state;
        this.entity = entity;
    }

    /**
     * @return The state of the event
     */
    public final EventState getState() {
        return this.state;
    }

    /**
     * @return The {@code EntityLivingBase} that is jumping
     */
    public final EntityLivingBase getEntity() {
        return this.entity;
    }

    @Override
    public String toString() {
        return "EntityJumpEvent{" +
                "state=" + state +
                ", entity=" + entity +
                '}';
    }
}
