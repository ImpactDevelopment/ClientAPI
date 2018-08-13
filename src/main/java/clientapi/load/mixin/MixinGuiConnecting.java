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
import clientapi.event.defaults.game.network.ServerEvent;
import clientapi.util.interfaces.Helper;
import net.minecraft.client.multiplayer.GuiConnecting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Brady
 * @since 9/7/2017
 */
@Mixin(GuiConnecting.class)
public class MixinGuiConnecting {

    @Inject(method = "connect", at = @At("HEAD"))
    private void onPreConnect(CallbackInfo info) {
        ClientAPI.EVENT_BUS.post(new ServerEvent.Connect(ServerEvent.Connect.State.PRE, Helper.mc.getCurrentServerData()));
    }

    @Inject(method = "connect", at = @At(value = "INVOKE", target = "net/minecraft/client/Minecraft.displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V"))
    private void onError(CallbackInfo info) {
        ClientAPI.EVENT_BUS.post(new ServerEvent.Connect(ServerEvent.Connect.State.FAILED, Helper.mc.getCurrentServerData()));
    }

    @Inject(method = "connect", at = @At(value = "INVOKE_ASSIGN", target = "net/minecraft/network/NetworkManager.sendPacket(Lnet/minecraft/network/Packet;)V", ordinal = 1))
    private void onSendPacket(CallbackInfo info) {
        ClientAPI.EVENT_BUS.post(new ServerEvent.Connect(ServerEvent.Connect.State.CONNECT, Helper.mc.getCurrentServerData()));
    }
}
