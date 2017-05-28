package me.zero.client.api.event.defaults;

import me.zero.alpine.type.Cancellable;
import net.minecraft.block.Block;

/**
 * Called when the collision state of a block is checked.
 * When the event is cancelled, the collision check returns
 * false.
 *
 * @author Brady
 * @since 4/8/2017 12:00 PM
 */
public final class BlockCollisionEvent extends Cancellable {

    /**
     * Block receiving collision check
     */
    private final Block block;

    public BlockCollisionEvent(Block block) {
        this.block = block;
    }

    /**
     * @return The block receiving a collision check
     */
    public final Block getBlock() {
        return this.block;
    }
}
