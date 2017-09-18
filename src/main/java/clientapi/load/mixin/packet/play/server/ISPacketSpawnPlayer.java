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

import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSpawnPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.UUID;

/**
 * @author Brady
 * @since 9/10/2017 3:30 PM
 */
@Mixin(SPacketSpawnPlayer.class)
public interface ISPacketSpawnPlayer {

    @Accessor int getEntityId();

    @Accessor void setEntityId(int entityId);

    @Accessor UUID getUniqueId();

    @Accessor void setUniqueId(UUID uniqueId);

    @Accessor double getX();

    @Accessor void setX(double x);

    @Accessor double getY();

    @Accessor void setY(double y);

    @Accessor double getZ();

    @Accessor void setZ(double z);

    @Accessor byte getYaw();

    @Accessor void setYaw(byte yaw);

    @Accessor byte getPitch();

    @Accessor void setPitch(byte pitch);

    @Accessor EntityDataManager getWatcher();

    @Accessor void setWatcher(EntityDataManager watcher);

    @Accessor List<EntityDataManager.DataEntry<?>> getDataManagerEntries();

    @Accessor void setDataManagerEntries(List<EntityDataManager.DataEntry<?>> dataManagerEntries);
}
