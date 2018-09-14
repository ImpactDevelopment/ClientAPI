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

package clientapi.util;

import clientapi.util.interfaces.Helper;
import clientapi.util.math.Vec3;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

/**
 * Generic Block Utils
 *
 * @author Brady
 * @since 2/24/2017
 */
public final class BlockUtils implements Helper {

    private BlockUtils() {}

    /**
     * Gets the block at the specified coordinates
     *
     * @param x The x position of the block
     * @param y The y position of the block
     * @param z The z position of the block
     * @return The block, returns null if the world isn't initialized
     */
    public static Block getBlock(int x, int y, int z) {
        return getBlock(new BlockPos(x, y, z));
    }

    /**
     * Gets the block at the specified coordinates
     *
     * @param x The x position of the block
     * @param y The y position of the block
     * @param z The z position of the block
     * @return The block, returns null if the world isn't initialized
     */
    public static Block getBlock(double x, double y, double z) {
        return getBlock(new BlockPos(x, y, z));
    }

    /**
     * Gets the block at the coordinates of the specified {@link Vec3}
     *
     * @param vec The vector position
     * @return The block, returns null if the world isn't initialized
     */
    public static Block getBlock(Vec3 vec) {
        Objects.requireNonNull(vec);
        return getBlock(new BlockPos(vec.getX(), vec.getY(), vec.getZ()));
    }

    /**
     * Gets the block at the specified {@link BlockPos}
     *
     * @param pos The position of the block
     * @return The block, returns null if the world isn't initialized
     */
    public static Block getBlock(BlockPos pos) {
        return mc.world == null ? null : mc.world.getBlockState(pos).getBlock();
    }

    /**
     * Gets the first block colliding with the
     * bounding box of the specified entity.
     *
     * @param e The entity
     * @return The block, returns null if the world isn't initialized
     */
    public static Block getBlock(Entity e) {
        return getBlock(e.getEntityBoundingBox());
    }

    /**
     * Gets the first block colliding with the offset
     * bounding box of the specified entity.
     *
     * @param e The entity
     * @param offset The bounding box offset applied
     * @return The block, returns null if the world isn't initialized
     */
    public static Block getBlock(Entity e, Vec3 offset) {
        return getBlock(e.getEntityBoundingBox().offset(offset.getX(), offset.getY(), offset.getZ()));
    }

    /**
     * Gets the first block that collides with the
     * specified bounding box.
     *
     * @param bb The bounding box
     * @return The block, returns null if the world isn't initialized
     */
    public static Block getBlock(AxisAlignedBB bb) {
        int y = (int) bb.minY;
        for (int x = (int) Math.floor(bb.minX); x < (int) Math.floor(bb.maxX) + 1; x++) {
            for (int z = (int) Math.floor(bb.minZ); z < (int) Math.floor(bb.maxZ) + 1; z++) {
                Block block =  getBlock(new BlockPos(x, y, z));
                if (block != Blocks.AIR) {
                    return block;
                }
            }
        }
        return null;
    }
}
