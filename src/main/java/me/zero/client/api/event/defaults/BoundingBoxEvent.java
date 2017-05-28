package me.zero.client.api.event.defaults;

import me.zero.alpine.type.Cancellable;
import net.minecraft.block.Block;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

/**
 * Called from Block#addCollisionBoxToList(BlockPos, AxisAlignedBB, List, AxisAlignedBB).
 * Used to hook into block collision, used to modify the bounding boxes of blocks.
 *
 * @author Brady
 * @since 4/10/2017 12:00 PM
 */
public final class BoundingBoxEvent extends Cancellable {

    /**
     * The block itself
     */
    private final Block block;

    /**
     * The position of the block
     */
    private final BlockPos pos;

    /**
     * The bounding box of the block
     */
    private AxisAlignedBB aabb;

    public BoundingBoxEvent(Block block, BlockPos pos, AxisAlignedBB aabb) {
        this.block = block;
        this.pos = pos;
        this.aabb = aabb;
    }

    /**
     * Sets the block's bounding box
     *
     * @param aabb New bounding box
     * @return This event
     */
    public final BoundingBoxEvent setBoundingBox(AxisAlignedBB aabb) {
        this.aabb = aabb;
        return this;
    }

    /**
     * @return The block being collided with
     */
    public final Block getBlock() {
        return this.block;
    }

    /**
     * @return The position of the block being collided with
     */
    public final BlockPos getPos() {
        return this.pos;
    }

    /**
     * @return The bounding box of the Block
     */
    public final AxisAlignedBB getBoundingBox() {
        return this.aabb;
    }
}
