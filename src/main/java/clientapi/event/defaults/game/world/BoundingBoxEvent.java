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

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Called from {@link Block#addCollisionBoxToList(BlockPos, AxisAlignedBB, List, AxisAlignedBB)}.
 * Used to hook into block collision, used to modify the bounding boxes of blocks.
 *
 * @author Brady
 * @since 4/10/2017
 */
public final class BoundingBoxEvent {

    /**
     * Singleton instance of this event
     */
    private static final BoundingBoxEvent EVENT = new BoundingBoxEvent();

    /**
     * The block itself
     */
    private Block block;

    /**
     * The position of the block
     */
    private BlockPos pos;

    /**
     * The bounding box of the block
     */
    private AxisAlignedBB aabb;

    /**
     * Colliding list being added onto
     */
    private List<AxisAlignedBB> collidingBoxes;

    /**
     * Entity being checked, may be {@code null}
     */
    private Entity entity;

    /**
     * Sets the block's bounding box. Setting it to
     * {@code null} will effectively cancel the call.
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

    /**
     * @return The current collision box list
     */
    public final List<AxisAlignedBB> getCollidingBoxes() {
        return this.collidingBoxes;
    }

    /**
     * @return Entity being checked, may be {@code null}
     */
    public final Entity getEntity() {
        return this.entity;
    }

    @Override
    public String toString() {
        return "BoundingBoxEvent{" +
                "block=" + block +
                ", pos=" + pos +
                ", aabb=" + aabb +
                ", collidingBoxes=" + collidingBoxes +
                ", entity=" + entity +
                '}';
    }

    public static BoundingBoxEvent get(Block block, BlockPos pos, AxisAlignedBB aabb, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity) {
        EVENT.block = block;
        EVENT.pos = pos;
        EVENT.aabb = aabb;
        EVENT.collidingBoxes = collidingBoxes;
        EVENT.entity = entity;
        return EVENT;
    }
}
