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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketClientSettings;
import net.minecraft.util.EnumHandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:14 PM
 */
@Mixin(CPacketClientSettings.class)
public interface ICPacketClientSettings {

    @Accessor String getLang();

    @Accessor void setLang(String lang);

    @Accessor int getView();

    @Accessor void setView(int view);

    @Accessor EntityPlayer.EnumChatVisibility getChatVisibility();

    @Accessor void setChatVisibility(EntityPlayer.EnumChatVisibility chatVisibility);

    @Accessor boolean isEnableColors();

    @Accessor void setEnableColors(boolean enabledColors);

    @Accessor int getModelPartFlags();

    @Accessor void setModelPartFlags(int modelPartFlags);

    @Accessor EnumHandSide getMainHand();

    @Accessor void setMainHand(EnumHandSide mainHand);
}
