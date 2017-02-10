package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;
import net.minecraft.network.Packet;

/**
 * Called whenever a packet is either sent or received
 *
 * @since 1.0
 *
 * Created by Brady on 2/7/2017.
 */
public class PacketEvent extends Cancellable {

    private Packet<?> packet;
    private Type type;

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

    public enum Type {
        SEND, RECEIVE
    }
}
