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

import net.minecraft.network.play.server.SPacketMaps;
import net.minecraft.world.storage.MapDecoration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 9/10/2017 12:13 PM
 */
@Mixin(SPacketMaps.class)
public interface ISPacketMaps {

    @Accessor int getMapId();

    @Accessor void setMapId(int mapId);

    @Accessor byte getMapScale();

    @Accessor void setMapScale(byte mapScale);

    @Accessor boolean isTrackingPosition();

    @Accessor void setTrackingPosition(boolean trackingPosition);

    @Accessor MapDecoration[] getIcons();

    @Accessor void setIcons(MapDecoration[] icons);

    @Accessor int getMinX();

    @Accessor void setMinX(int minX);

    @Accessor int getMinZ();

    @Accessor void setMinZ(int minZ);

    @Accessor int getColumns();

    @Accessor void setColumns(int columns);

    @Accessor int getRows();

    @Accessor void setRows(int rows);

    @Accessor byte getMapDataBytes();

    @Accessor void setMapDataBytes(byte mapDataBytes);
}
