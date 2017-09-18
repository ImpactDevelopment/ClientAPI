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

import net.minecraft.network.play.server.SPacketWorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 9/10/2017 4:20 PM
 */
@Mixin(SPacketWorldBorder.class)
public interface ISPacketWorldBorder {

    @Accessor SPacketWorldBorder.Action getAction();

    @Accessor void setAction(SPacketWorldBorder.Action aciton);

    @Accessor int getSize();

    @Accessor void setSize(int size);

    @Accessor double getCenterX();

    @Accessor void setCenterX(double centerX);

    @Accessor double getCenterZ();

    @Accessor void setCenterZ(double centerZ);

    @Accessor double getTargetSize();

    @Accessor void setTargetSize(double targetSize);

    @Accessor double getDiameter();

    @Accessor void setDiameter(double diameter);

    @Accessor long getTimeUntilTarget();

    @Accessor void setTimeUntilTarget(long timeUntilTarget);

    @Accessor int getWarningTime();

    @Accessor void setWarningTime(int warningTime);

    @Accessor int getWarningDistance();

    @Accessor void setWarningDistance(int warningDistance);
}
