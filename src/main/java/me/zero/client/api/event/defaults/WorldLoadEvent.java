package me.zero.client.api.event.defaults;

import net.minecraft.client.multiplayer.WorldClient;

/**
 * Called when a world is loaded.
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/9/2017 12:00PM
 */
public final class WorldLoadEvent {

    /**
     * World being loaded
     */
    private final WorldClient world;

    public WorldLoadEvent(WorldClient world) {
        this.world = world;
    }

    /**
     * @since 1.0
     *
     * @return The world being loaded
     */
    public final WorldClient getWorld() {
        return this.world;
    }
}
