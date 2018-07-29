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
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(Block.class)
public abstract class MixinBlock {

    private BoundingBoxEvent bbEvent;

    @Inject(method = "isCollidable(Lnet/minecraft/block/state/IBlockState;)Z", at = @At("HEAD"), cancellable = true)
    private void isCollidable(IBlockState state, CallbackInfoReturnable<Boolean> cir) {
        BlockCollisionEvent event = new BlockCollisionEvent((Block) (Object) this);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            cir.setReturnValue(false);
    }

    // TODO: Reverse VoxelShape and all of that shenanigans to fix this!

    /*
    @Inject(method = "addCollisionBoxToList(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/List;Lnet/minecraft/entity/Entity;Z)V",
            at = @At("HEAD"))
    private void addCollisionBox(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entity, boolean isActualState, CallbackInfo ci) {
        synchronized (this) {
            Block block = (Block) (Object) (this);
            bbEvent = new BoundingBoxEvent(block, pos, block.getCollisionBoundingBox(state, world, pos), collidingBoxes, entity);
            ClientAPI.EVENT_BUS.post(bbEvent);
        }
    }

    @Redirect(method = "addCollisionBoxToList(Lnet/minecraft/block/state/IBlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/AxisAlignedBB;Ljava/util/List;Lnet/minecraft/entity/Entity;Z)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;getCollisionBoundingBox(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/AxisAlignedBB;"))
    private AxisAlignedBB getBB(IBlockState state, IBlockAccess world, BlockPos pos) {
        synchronized (this) {
            AxisAlignedBB bb = (bbEvent == null) ?
                    state.getCollisionBoundingBox(world, pos) :
                    bbEvent.getBoundingBox();
            bbEvent = null;
            return bb;
        }
    }
    */
}
