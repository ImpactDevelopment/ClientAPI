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

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.play.server.SPacketAdvancementInfo;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import java.util.Set;

/**
 * @author Brady
 * @since 9/10/2017 1:15 AM
 */
@Mixin(SPacketAdvancementInfo.class)
public interface ISPacketAdvancementInfo {

    @Accessor boolean isFirstSync();

    @Accessor void setFirstSync(boolean firstSync);

    @Accessor Map<ResourceLocation, Advancement.Builder> getAdvancementsToAdd();

    @Accessor void setAdvancementsToAdd(Map<ResourceLocation, Advancement.Builder> advancementsToAdd);

    @Accessor Set<ResourceLocation> getAdvancementsToRemove();

    @Accessor void setAdvancementsToRemove(Set<ResourceLocation> advancementsToRemove);

    @Accessor Map<ResourceLocation, AdvancementProgress> getProgressUpdates();

    @Accessor void setProgressUpdates(Map<ResourceLocation, AdvancementProgress> progressUpdates);
}
