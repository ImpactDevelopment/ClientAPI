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

import me.zero.alpine.event.ICancellable;
import net.minecraft.client.entity.EntityPlayerSP;

/**
 * @author Brady
 * @since 11/16/2018
 */
public class LocalPlayerEvent {

    /**
     * The local player that the event relates to
     */
    private final EntityPlayerSP player;

    public LocalPlayerEvent(EntityPlayerSP player) {
        this.player = player;
    }

    /**
     * @return The local player that the event relates to
     */
    public final EntityPlayerSP getPlayer() {
        return this.player;
    }

    public static class Cancellable extends LocalPlayerEvent implements ICancellable {

        private boolean cancelled;

        public Cancellable(EntityPlayerSP player) {
            super(player);
        }

        @Override
        public void cancel() {
            this.cancelled = true;
        }

        @Override
        public boolean isCancelled() {
            return this.cancelled;
        }
    }
}
