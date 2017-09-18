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

import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:49 PM
 */
@Mixin(CPacketUseEntity.class)
public interface ICPacketUseEntity {

    @Accessor int getEntityId();

    @Accessor void setEntityId(int entityId);

    @Accessor CPacketUseEntity.Action getAction();

    @Accessor void setAction(CPacketUseEntity.Action action);

    @Accessor Vec3d getHitVec();

    @Accessor void setHitVec(Vec3d hitVec);

    @Accessor EnumHand getHand();

    @Accessor void setHand(EnumHand hand);
}
