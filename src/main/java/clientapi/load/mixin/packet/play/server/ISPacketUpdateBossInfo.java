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

import net.minecraft.network.play.server.SPacketUpdateBossInfo;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.UUID;

/**
 * @author Brady
 * @since 9/10/2017 4:00 PM
 */
@Mixin(SPacketUpdateBossInfo.class)
public interface ISPacketUpdateBossInfo {

    @Accessor UUID getUniqueId();

    @Accessor void setUniqueId(UUID uniqueId);

    @Accessor SPacketUpdateBossInfo.Operation getOperation();

    @Accessor void setOperation(SPacketUpdateBossInfo.Operation operation);

    @Accessor ITextComponent getName();

    @Accessor void setName(ITextComponent name);

    @Accessor float getPercent();

    @Accessor void setPercent(float percent);

    @Accessor BossInfo.Color getColor();

    @Accessor void setColor(BossInfo.Color color);

    @Accessor BossInfo.Overlay getOverlay();

    @Accessor void setOverlay(BossInfo.Overlay overlay);

    @Accessor boolean isDarkenSky();

    @Accessor void setDarkenSky();

    @Accessor boolean isPlayEndBossMusic();

    @Accessor void setPlayEndBossMusic(boolean playEndBossMusic);

    @Accessor boolean isCreateFog();

    @Accessor void setCreateFog(boolean createFog);
}
