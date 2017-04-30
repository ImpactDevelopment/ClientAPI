package me.zero.client.load.mixin;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.defaults.PacketEvent;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

import javax.annotation.Nullable;

/**
 * Created by Brady on 4/27/2017.
 */
@Mixin(NetworkManager.class)
public abstract class MixinNetworkManager {

    @Shadow public abstract void dispatchPacket(final Packet<?> inPacket, @Nullable final GenericFutureListener<? extends Future<?super Void>>[] futureListeners);

    @Redirect(method = "channelRead0", at = @At(value = "INVOKE", target = "net/minecraft/network/Packet.processPacket(Lnet/minecraft/network/INetHandler;)V"))
    @SuppressWarnings("unchecked")
    public void processPacket(Packet<?> packetIn, INetHandler handler) {
        PacketEvent event = new PacketEvent(packetIn, PacketEvent.Type.RECEIVE);
        EventManager.post(event);
        if (event.isCancelled())
            return;

        ((Packet<INetHandler>) event.getPacket()).processPacket(handler);
    }

    @Redirect(method = "sendPacket", at = @At(value = "INVOKE", target = "net/minecraft/network/NetworkManager.dispatchPacket(Lnet/minecraft/network/Packet;[Lio/netty/util/concurrent/GenericFutureListener;)V"))
    public void sendPacket(NetworkManager networkManager, Packet<?> packetIn, @Nullable final GenericFutureListener<? extends Future<?super Void>>[] futureListeners) {
        PacketEvent event = new PacketEvent(packetIn, PacketEvent.Type.SEND);
        EventManager.post(event);
        if (event.isCancelled())
            return;

        this.dispatchPacket(event.getPacket(), null);
    }
}
