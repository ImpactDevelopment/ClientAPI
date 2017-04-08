package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;
import net.minecraft.block.Block;

/**
 * Called when the collision state of a block is checked.
 * When the event is cancelled, the collision check returns
 * false.
 *
 * @since 1.0
 *
 * Created by Brady on 4/8/2017.
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
     * @since 1.0
     *
     * @return The block receiving a collision check
     */
    public Block getBlock() {
        return this.block;
    }
}
