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

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.play.client.CPacketRecipeInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:28 PM
 */
@Mixin(CPacketRecipeInfo.class)
public interface ICPacketRecipeInfo {

    @Accessor CPacketRecipeInfo.Purpose getPurpose();

    @Accessor void setPurpose(CPacketRecipeInfo.Purpose purpose);

    @Accessor IRecipe getRecipe();

    @Accessor void setRecipe(IRecipe recipe);

    @Accessor("isGuiOpen") boolean isGuiOpen();

    @Accessor("isGuiOpen") void setGuiOpen(boolean isGuiOpen);

    @Accessor boolean isFilteringCraftable();

    @Accessor void setFilteringCraftable(boolean filteringCraftable);
}
