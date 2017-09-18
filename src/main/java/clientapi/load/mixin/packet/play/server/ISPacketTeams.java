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

import net.minecraft.network.play.server.SPacketTeams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Collection;

/**
 * @author Brady
 * @since 9/10/2017 3:45 PM
 */
@Mixin(SPacketTeams.class)
public interface ISPacketTeams {

    @Accessor String getName();

    @Accessor void setName(String name);

    @Accessor String getDisplayName();

    @Accessor void setDisplayName(String displayName);

    @Accessor String getPrefix();

    @Accessor void setPrefix(String prefix);

    @Accessor String getSuffix();

    @Accessor void setSuffix(String suffix);

    @Accessor String getNameTagVisibility();

    @Accessor void setNameTagVisibility(String nameTagVisibility);

    @Accessor String getCollisionRule();

    @Accessor void setCollisionRule(String collisionRule);

    @Accessor int getColor();

    @Accessor void setColor(int color);

    @Accessor Collection<String> getPlayers();

    @Accessor void setPlayers(Collection<String> players);

    @Accessor int getAction();

    @Accessor void setAction(int action);

    @Accessor int getFriendlyFlags();

    @Accessor void setFriendlyFlags(int friendlyFlags);
}
