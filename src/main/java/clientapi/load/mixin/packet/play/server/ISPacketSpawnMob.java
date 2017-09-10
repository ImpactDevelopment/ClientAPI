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

import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketSpawnMob;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;
import java.util.UUID;

/**
 * @author Brady
 * @since 9/10/2017 2:43 PM
 */
@Mixin(SPacketSpawnMob.class)
public interface ISPacketSpawnMob {

    @Accessor int getEntityId();

    @Accessor void setEntityId(int entityId);

    @Accessor UUID getUniqueId();

    @Accessor void setUniqueId(UUID uniqueId);

    @Accessor int getType();

    @Accessor void setType(int type);

    @Accessor double getX();

    @Accessor void setX(double x);

    @Accessor double getY();

    @Accessor void setY(double y);

    @Accessor double getZ();

    @Accessor void setZ(double z);

    @Accessor int getVelocityX();

    @Accessor void setVelocityX(int velocityX);

    @Accessor int getVelocityY();

    @Accessor void setVelocityY(int velocityY);

    @Accessor int getVelocityZ();

    @Accessor void setVelocityZ(int velocityZ);

    @Accessor byte getYaw();

    @Accessor void setYaw(byte yaw);

    @Accessor byte getPitch();

    @Accessor void setPitch(byte pitch);

    @Accessor byte getHeadPitch();

    @Accessor void setHeadPitch(byte headPitch);

    @Accessor EntityDataManager getDataManager();

    @Accessor void setDataManager(EntityDataManager dataManager);

    @Accessor List<EntityDataManager.DataEntry<?>> getDataManagerEntries();

    @Accessor void setDataManagerEntries(List<EntityDataManager.DataEntry<?>> dataManagerEntries);
}
