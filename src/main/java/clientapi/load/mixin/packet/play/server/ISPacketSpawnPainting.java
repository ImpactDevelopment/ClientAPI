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

import net.minecraft.network.play.server.SPacketSpawnPainting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.UUID;

/**
 * @author Brady
 * @since 9/10/2017 3:25 PM
 */
@Mixin(SPacketSpawnPainting.class)
public interface ISPacketSpawnPainting {

    @Accessor int getEntityId();

    @Accessor void setEntityId(int entityId);

    @Accessor UUID getUniqueId();

    @Accessor void setUniqueId(UUID uniqueId);

    @Accessor BlockPos getPosition();

    @Accessor void setPosition(BlockPos position);

    @Accessor EnumFacing getFacing();

    @Accessor void setFacing(EnumFacing facing);

    @Accessor String getTitle();

    @Accessor void setTitle(String title);
}
