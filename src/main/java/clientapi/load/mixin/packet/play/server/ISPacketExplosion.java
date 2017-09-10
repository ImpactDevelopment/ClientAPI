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

import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * @author Brady
 * @since 9/10/2017 11:47 AM
 */
@Mixin(SPacketExplosion.class)
public interface ISPacketExplosion {

    @Accessor double getX();

    @Accessor void setX(double x);

    @Accessor double getY();

    @Accessor void setY(double y);

    @Accessor double getZ();

    @Accessor void setZ(double z);

    @Accessor float getStrength();

    @Accessor void setStrength(float strength);

    @Accessor List<BlockPos> getAffectedBlockPositions();

    @Accessor void setAffectedBlockPositions(List<BlockPos> affectedBlockPositions);

    @Accessor float getMotionX();

    @Accessor void setMotionX(float motionX);

    @Accessor float getMotionZ();

    @Accessor void setMotionZ(float motionZ);

    @Accessor float getMotionY();

    @Accessor void setMotionY(float motionY);
}
