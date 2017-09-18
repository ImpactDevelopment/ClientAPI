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

package clientapi.load.mixin.packet.play.server;

import net.minecraft.network.play.server.SPacketEffect;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 9/10/2017 2:36 AM
 */
@Mixin(SPacketEffect.class)
public interface ISPacketEffect {

    @Accessor int getSoundType();

    @Accessor void setSoundType(int soundType);

    @Accessor BlockPos getSoundPos();

    @Accessor void setSoundPos(BlockPos soundPos);

    @Accessor int getSoundData();

    @Accessor void setSoundData(int soundData);

    @Accessor boolean isServerWide();

    @Accessor void setServerWide(boolean serverWide);
}
