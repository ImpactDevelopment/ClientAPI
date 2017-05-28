package me.zero.client.api.event.defaults;

import net.minecraft.client.multiplayer.WorldClient;

/**
 * Called when the local world is modified.
 * This can either be loading a new world or
 * unloading the current world.
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
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
}
