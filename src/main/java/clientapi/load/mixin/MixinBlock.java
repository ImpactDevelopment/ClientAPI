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

package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.world.BlockCollisionEvent;
import clientapi.event.defaults.game.world.BoundingBoxEvent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(Block.class)
public abstract class MixinBlock {

    private BoundingBoxEvent bbEvent;

    @Inject(method = "canCollideCheck", at = @At("HEAD"), cancellable = true)
    private void canCollideCheck(IBlockState state, boolean hitIfLiquid, CallbackInfoReturnable<Boolean> cir) {
        BlockCollisionEvent event = new BlockCollisionEvent((Block) (Object) this);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            cir.setReturnValue(false);
    }

    @Inject(method = "addCollisionBoxToList(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/List;Lnet/minecraft/entity/Entity;Z)V",
            at = @At("HEAD"))
    private void in(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState, CallbackInfo ci) {
        Block block = (Block) (Object) (this);
        bbEvent = new BoundingBoxEvent(block, pos, block.getCollisionBoundingBox(state, world, pos), collidingBoxes, entity);
        ClientAPI.EVENT_BUS.post(bbEvent);
    }

    @Redirect(method = "addCollisionBoxToList(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/List;Lnet/minecraft/entity/Entity;Z)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getCollisionBoundingBox(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/AxisAlignedBB;"))
    private AxisAlignedBB getBB(IBlockState state, IBlockAccess world, BlockPos pos) {
        AxisAlignedBB bb = (bbEvent == null) ?
                state.getCollisionBoundingBox(world, pos):
                bbEvent.getBoundingBox();
        bbEvent = null;
        return bb;
    }
}
