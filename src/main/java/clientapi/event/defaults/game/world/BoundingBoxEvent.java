/*
 * Copyright 2017 ZeroMemes
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
