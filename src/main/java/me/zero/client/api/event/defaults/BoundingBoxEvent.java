package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;
import net.minecraft.block.Block;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

/**
 * Called from Block#addCollisionBoxToList(BlockPos, AxisAlignedBB, List, AxisAlignedBB).
 * Used to hook into block collision, used to modify the bounding boxes of blocks.
 *
 * @since 1.0
 *
 * Created by Brady on 4/10/2017.
 */
public class BoundingBoxEvent extends Cancellable {

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
     * @since 1.0
     *
     * @param aabb New bounding box
     * @return This event
     */
    public BoundingBoxEvent setBoundingBox(AxisAlignedBB aabb) {
        this.aabb = aabb;
        return this;
    }

    /**
     * @since 1.0
     *
     * @return The block being collided with
     */
    public Block getBlock() {
        return this.block;
    }

    /**
     * @since 1.0
     *
     * @return The position of the block being collided with
     */
    public BlockPos getPos() {
        return this.pos;
    }

    /**
     * @since 1.0
     *
     * @return The bounding box of the Block
     */
    public AxisAlignedBB getBoundingBox() {
        return this.aabb;
    }
}
