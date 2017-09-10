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

import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.util.EnumParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 9/10/2017 12:41 PM
 */
@Mixin(SPacketParticles.class)
public interface ISPacketParticles {

    @Accessor EnumParticleTypes getParticleType();

    @Accessor void setParticleType(EnumParticleTypes particleType);

    @Accessor float getXCoord();

    @Accessor void setXCoord(float xCoord);

    @Accessor float getYCoord();

    @Accessor void setYCoord(float yCoord);

    @Accessor float getZCoord();

    @Accessor void setZCoord(float zCoord);

    @Accessor float getXOffset();

    @Accessor void setXOffset(float xOffset);

    @Accessor float getYOffset();

    @Accessor void setYOffset(float yOffset);

    @Accessor float getZOffset();

    @Accessor void setZOffset(float zOffset);

    @Accessor float getParticleSpeed();

    @Accessor void setParticleSpeed(float particleSpeed);

    @Accessor int getParticleCount();

    @Accessor void setParticleCount(int particleCount);

    @Accessor boolean isLongDistance();

    @Accessor void setLongDistance(boolean longDistance);

    @Accessor int[] getParticleArguments();

    @Accessor void setParticleArguments(int[] particleArguments);
}
