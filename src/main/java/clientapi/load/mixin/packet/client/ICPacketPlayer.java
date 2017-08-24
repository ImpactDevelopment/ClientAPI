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

package clientapi.load.mixin.packet.client;

import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
@Mixin(CPacketPlayer.class)
public interface ICPacketPlayer {

    @Accessor void setX(double x);

    @Accessor double getX();

    @Accessor void setY(double y);

    @Accessor double getY();

    @Accessor void setZ(double z);

    @Accessor double getZ();

    @Accessor void setYaw(float yaw);

    @Accessor float getYaw();

    @Accessor void setPitch(float pitch);

    @Accessor float getPitch();

    @Accessor void setOnGround(boolean onGround);

    @Accessor boolean getOnGround();

    @Accessor void setMoving(boolean moving);

    @Accessor boolean getMoving();

    @Accessor void setRotating(boolean rotating);

    @Accessor boolean getRotating();
}
