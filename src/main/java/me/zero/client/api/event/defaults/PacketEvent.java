package me.zero.client.api.event.defaults;

import me.zero.client.api.util.interfaces.Cancellable;
import net.minecraft.network.Packet;

/**
 * Called whenever a packet is either sent or received
 *
 * @since 1.0
 *
 * Created by Brady on 2/7/2017.
 */
public final class PacketEvent implements Cancellable {

    private Packet<?> packet;
    private Type type;
    private boolean cancelled;

    public PacketEvent(Packet<?> packet, Type type) {
        this.packet = packet;
        this.type = type;
    }

    public Packet<?> getPacket() {
        return this.packet;
    }

    public Packet<?> setPacket(Packet<?> packet) {
        return this.packet = packet;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public enum Type {
        SEND, RECEIVE
    }
}
