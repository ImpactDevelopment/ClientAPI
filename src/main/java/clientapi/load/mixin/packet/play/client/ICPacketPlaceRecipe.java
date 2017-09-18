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
import net.minecraft.network.play.client.CPacketPlaceRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:56 PM
 */
@Mixin(CPacketPlaceRecipe.class)
public interface ICPacketPlaceRecipe {

    @Accessor("field_194320_a") int getWindowId();

    @Accessor("field_194320_a") void setWindowId(int windowId);

    @Accessor("field_194321_b") IRecipe getLastClickedRecipe();

    @Accessor("field_194321_b") void setLastClickedRecipe(IRecipe lastClickedRecipe);

    @Accessor("field_194322_c") boolean isCraftStack();

    @Accessor("field_194322_c") void setCraftStack(boolean craftStack);
}
