/*
 * Copyright 2017 ImpactDevelopment
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

package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:14 PM
 */
@Mixin(CPacketPlayerTryUseItemOnBlock.class)
public interface ICPacketPlayerTryUseItemOnBlock {

    @Accessor BlockPos getPosition();

    @Accessor void setPosition(BlockPos position);

    @Accessor EnumFacing getPlacedBlockDirection();

    @Accessor void setPlacedBlockDirection(EnumFacing placedBlockDirection);

    @Accessor EnumHand getHand();

    @Accessor void setHand(EnumHand hand);

    @Accessor float getFacingX();

    @Accessor void setFacingX(float facingX);

    @Accessor float getFacingY();

    @Accessor void setFacingY(float facingY);

    @Accessor float getFacingZ();

    @Accessor void setFacingZ(float facingZ);
}
