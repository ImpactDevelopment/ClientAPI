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

import net.minecraft.network.play.server.SPacketPlayerListItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * @author Brady
 * @since 9/10/2017 1:24 PM
 */
@Mixin(SPacketPlayerListItem.class)
public interface ISPacketPlayerListItem {

    @Accessor SPacketPlayerListItem.Action getAction();

    @Accessor void setAction(SPacketPlayerListItem.Action action);

    @Accessor List<SPacketPlayerListItem.AddPlayerData> getPlayers();

    @Accessor void setPlayers(List<SPacketPlayerListItem.AddPlayerData> players);
}
