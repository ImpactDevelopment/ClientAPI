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
import net.minecraft.network.play.server.SPacketEntityMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * @author Brady
 * @since 9/10/2017 2:55 AM
 */
@Mixin(SPacketEntityMetadata.class)
public interface ISPacketEntityMetadata {

    @Accessor int getEntityId();

    @Accessor void setEntityId(int entityId);

    @Accessor List<EntityDataManager.DataEntry<?>> getDataManagerEntries();

    @Accessor void setDataManagerEntries(List<EntityDataManager.DataEntry<?>> dataManagerEntries);
}
