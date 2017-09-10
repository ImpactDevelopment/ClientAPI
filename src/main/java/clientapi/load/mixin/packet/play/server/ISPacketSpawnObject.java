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

import net.minecraft.network.play.server.SPacketSpawnObject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.UUID;

/**
 * @author Brady
 * @since 9/10/2017 2:55 PM
 */
@Mixin(SPacketSpawnObject.class)
public interface ISPacketSpawnObject {

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

    @Accessor int getSpeedX();

    @Accessor void setSpeedX(int speedX);

    @Accessor int getSpeedY();

    @Accessor void setSpeedY(int speedY);

    @Accessor int getSpeedZ();

    @Accessor void setSpeedZ(int speedZ);

    @Accessor int getPitch();

    @Accessor void setPitch(int pitch);

    @Accessor int getYaw();

    @Accessor void setYaw(int yaw);

    @Accessor int getType();

    @Accessor void setType(int type);

    @Accessor int getData();

    @Accessor void setData(int data);
}
