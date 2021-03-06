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

package clientapi.event.defaults.game.entity.local;

import me.zero.alpine.event.EventState;
import net.minecraft.client.entity.EntityPlayerSP;

/**
 * Called before and after EntityPlayerSP#onLivingUpdate()
 *
 * @author Brady
 * @since 2/10/2017
 */
public final class LivingUpdateEvent extends LocalPlayerEvent {

    /**
     * The state of this event
     */
    private final EventState state;

    public LivingUpdateEvent(EntityPlayerSP player, EventState state) {
        super(player);
        this.state = state;
    }

    /**
     * @return The event state
     */
    public final EventState getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return "LivingUpdateEvent{" +
                "state=" + state +
                '}';
    }
}
