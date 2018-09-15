/*
 * Copyright 2018 ImpactDevelopment
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

package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.entity.PlayerDeathEvent;
import clientapi.event.defaults.game.misc.ChatEvent;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 8/24/2017
 */
@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {

    @Shadow private WorldClient world;

    @Inject(
            method = "handleCombatEvent",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "net/minecraft/network/PacketThreadUtil.checkThreadAndEnqueue(Lnet/minecraft/network/Packet;Lnet/minecraft/network/INetHandler;Lnet/minecraft/util/IThreadListener;)V"
            )
    )
    private void handleCombatEvent(SPacketCombatEvent event, CallbackInfo ci) {
        if (this.world == null)
            return;

        if (event.eventType == SPacketCombatEvent.Event.ENTITY_DIED) {
            Entity died = this.world.getEntityByID(event.playerId);
            Entity killer = this.world.getEntityByID(event.entityId);

            // The entity that died should always be a player, this is just a safety measure.
            // The killer isn't checked because in some cases it can be null.
            if (died instanceof EntityPlayer)
                ClientAPI.EVENT_BUS.post(new PlayerDeathEvent((EntityPlayer) died, (EntityLivingBase) killer));
        }
    }

    @Redirect(
            method = "handleChat",
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/client/gui/GuiIngame.addChatMessage(Lnet/minecraft/util/text/ChatType;Lnet/minecraft/util/text/ITextComponent;)V"
            )
    )
    private void handleChat$addChatMessage(GuiIngame guiIngame, ChatType chatTypeIn, ITextComponent message) {
        ChatEvent event = new ChatEvent.Receive(message);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled())
            return;

        guiIngame.addChatMessage(chatTypeIn, event.getMessage());
    }
}
