package me.zero.client.load.mixin;

import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.BlockCollisionEvent;
import me.zero.client.api.event.defaults.BoundingBoxEvent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(Block.class)
public abstract class MixinBlock {

    @Shadow public static void addCollisionBoxToList(BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable AxisAlignedBB blockBox) {}

    @Inject(method = "canCollideCheck", at = @At("HEAD"), cancellable = true)
    public void canCollideCheck(IBlockState state, boolean hitIfLiquid, CallbackInfoReturnable<Boolean> ci) {
        BlockCollisionEvent event = new BlockCollisionEvent((Block) (Object) this);
        EventManager.post(event);
        if (event.isCancelled())
            ci.setReturnValue(false);
    }

    @Overwrite
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
        Block block = (Block) (Object) (this);
        AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(state, worldIn, pos);

        BoundingBoxEvent event = new BoundingBoxEvent(block, pos, axisalignedbb);
        EventManager.post(event);
        if (event.isCancelled())
            return;

        axisalignedbb = event.getBoundingBox();

        addCollisionBoxToList(pos, entityBox, collidingBoxes, axisalignedbb);
    }
}
