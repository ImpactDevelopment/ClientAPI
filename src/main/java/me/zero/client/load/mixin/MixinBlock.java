/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.load.mixin;

import me.zero.client.api.ClientAPI;
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
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            ci.setReturnValue(false);
    }

    @Overwrite
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
        Block block = (Block) (Object) (this);
        AxisAlignedBB axisalignedbb = block.getCollisionBoundingBox(state, worldIn, pos);

        BoundingBoxEvent event = new BoundingBoxEvent(block, pos, axisalignedbb);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            return;

        axisalignedbb = event.getBoundingBox();

        addCollisionBoxToList(pos, entityBox, collidingBoxes, axisalignedbb);
    }
}
