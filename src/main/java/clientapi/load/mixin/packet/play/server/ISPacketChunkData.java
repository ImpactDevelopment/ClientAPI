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

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketChunkData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * @author Brady
 * @since 9/10/2017 1:58 AM
 */
@Mixin(SPacketChunkData.class)
public interface ISPacketChunkData {

    @Accessor int getChunkX();

    @Accessor void setChunkX(int chunkX);

    @Accessor int getChunkZ();

    @Accessor void setChunkZ(int chunkZ);

    @Accessor int getAvailableSections();

    @Accessor void setAvailableSections(int availableSections);

    @Accessor byte[] getBuffer();

    @Accessor void setBuffer(byte[] buffer);

    @Accessor List<NBTTagCompound> getTileEntityTags();

    @Accessor void setTileEntityTags(List<NBTTagCompound> tileEntityTags);

    @Accessor boolean isFullChunk();

    @Accessor void setFullChunk(boolean fullChunk);
}
