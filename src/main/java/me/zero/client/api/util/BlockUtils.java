package me.zero.client.api.util;

import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.math.Vec3;
import me.zero.client.wrapper.IEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

/**
 * Generic Block Utils
 *
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
public final class BlockUtils implements Helper {

    private BlockUtils() {}

    public static Block getBlock(int x, int y, int z) {
        return getBlock(new BlockPos(x, y, z));
    }

    public static Block getBlock(double x, double y, double z) {
        return getBlock(new BlockPos(x, y, z));
    }

    public static Block getBlock(Vec3 vec) {
        return getBlock(new BlockPos(vec.getX(), vec.getY(), vec.getZ()));
    }

    public static Block getBlock(BlockPos bp) {
        return mc.world == null ? null : mc.world.getBlockState(bp).getBlock();
    }

    public static Block getBlock(Entity e) {
        return getBlock(e.getEntityBoundingBox());
    }

    public static Block getBlock(Entity e, Vec3 offset) {
        return getBlock(e.getEntityBoundingBox().offset(offset.getX(), offset.getY(), offset.getZ()));
    }

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

    public static boolean isOnLiquid() {
        return getBlock(((IEntity) mc.player).getPos().sub(0, 0.15, 0)) instanceof BlockLiquid;
    }
}
