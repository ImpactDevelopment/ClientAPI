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

package clientapi.event.defaults.game.world;

import net.minecraft.client.multiplayer.WorldClient;

/**
 * Called when the local world is modified.
 * This can either be loading a new world or
 * unloading the current world.
 *
 * @author Brady
 * @since 2/9/2017
 */
public final class WorldEvent {

    private WorldEvent() {}

    /**
     * Called when the world is loaded
     */
    public static class Load {

        /**
         * World being loaded
         */
        private final WorldClient world;

        public Load(WorldClient world) {
            this.world = world;
        }

        /**
         * @return The world being loaded
         */
        public final WorldClient getWorld() {
            return this.world;
        }
    }

    /**
     * Called when the world is unloaded
     */
    public static class Unload {}

    @Override
    public String toString() {
        return "WorldEvent{}";
    }
}
