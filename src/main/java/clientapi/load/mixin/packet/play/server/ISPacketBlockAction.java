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

package clientapi.load.mixin.packet.play.server;

import net.minecraft.block.Block;
import net.minecraft.network.play.server.SPacketBlockAction;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 9/10/2017 1:25 AM
 */
@Mixin(SPacketBlockAction.class)
public interface ISPacketBlockAction {

    @Accessor BlockPos getBlockPosition();

    @Accessor void setBlockPosition(BlockPos blockPosition);

    @Accessor int getInstrument();

    @Accessor void setInstrument(int instrument);

    @Accessor int getPitch();

    @Accessor void setPitch(int pitch);

    @Accessor Block getBlock();

    @Accessor void setBlock(Block block);
}
