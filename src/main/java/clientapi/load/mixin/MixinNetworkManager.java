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

package clientapi.load.mixin;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.network.PacketEvent;

import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

import javax.annotation.Nullable;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author Brady
 * @since 4/27/2017 12:00 PM
 */
@Mixin(NetworkManager.class)
public abstract class MixinNetworkManager {

    @Shadow
    protected abstract void dispatchPacket(final Packet<?> inPacket, @Nullable
    final GenericFutureListener<? extends Future<? super Void>>[] futureListeners);

    @Redirect(method = "channelRead0", at = @At(value = "INVOKE",
        target = "net/minecraft/network/Packet.processPacket(Lnet/minecraft/network/INetHandler;)V"))
    @SuppressWarnings("unchecked")
    private void processPacket(Packet<?> packetIn, INetHandler handler) {
        PacketEvent event = new PacketEvent.Receive(packetIn);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled()) return;

        ((Packet<INetHandler>) event.getPacket()).processPacket(handler);
    }

    @Redirect(method = "sendPacket", at = @At(value = "INVOKE",
        target = "net/minecraft/network/NetworkManager.dispatchPacket(Lnet/minecraft/network/Packet;[Lio/netty/util/concurrent/GenericFutureListener;)V"))
    private void sendPacket(NetworkManager networkManager, Packet<?> packetIn,
        @Nullable
        final GenericFutureListener<? extends Future<? super Void>>[] futureListeners) {
        PacketEvent event = new PacketEvent.Send(packetIn);
        ClientAPI.EVENT_BUS.post(event);
        if (event.isCancelled()) return;

        this.dispatchPacket(event.getPacket(), null);
    }
}
