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

import me.zero.alpine.event.EventState;
import net.minecraft.client.Minecraft;

/**
 * Event called when the game loop runs.
 *
 * @see Minecraft#runGameLoop()
 *
 * @author Brady
 * @since 1/24/2017
 */
public final class LoopEvent {

    /**
     * The state of the event
     */
    private final EventState state;

    public LoopEvent(EventState state) {
        this.state = state;
    }

    /**
     * @return The state of the event
     */
    public final EventState getState() {
        return this.state;
    }

    @Override
    public String toString() {
        return "LoopEvent{" +
                "state=" + state +
                '}';
    }
}
