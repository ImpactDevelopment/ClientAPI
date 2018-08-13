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

package clientapi.event.defaults.game.core;

import me.zero.alpine.type.Cancellable;
import me.zero.alpine.type.EventState;
import net.minecraft.client.entity.EntityPlayerSP;

/**
 * Called at the head of {@code EntityPlayerSP#onUpdate}
 *
 * @see EntityPlayerSP#onUpdate()
 *
 * @author Brady
 * @since 2/8/2017
 */
public final class UpdateEvent extends Cancellable {

    private final EventState state;

    public UpdateEvent(EventState state) {
        this.state = state;
    }

    public EventState getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return "UpdateEvent{" +
                "state=" + state +
                '}';
    }
}
