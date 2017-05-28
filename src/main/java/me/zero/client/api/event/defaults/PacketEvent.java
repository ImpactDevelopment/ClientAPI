package me.zero.client.api.event.defaults;

import me.zero.alpine.type.Cancellable;
import net.minecraft.network.Packet;

/**
 * Called whenever a packet is either sent or received
 *
 * @author Brady
 * @since 2/7/2017 12:00 PM
 */
public final class PacketEvent extends Cancellable {

    /**
     * The packet being sent
     */
    private Packet<?> packet;

    /**
     * The flow direction, send or receive
     */
    private final Type type;

    public PacketEvent(Packet<?> packet, Type type) {
        this.packet = packet;
        this.type = type;
    }

    /**
     * @return The packet being sent/received
     */
    public final Packet<?> getPacket() {
        return this.packet;
    }

    /**
     * Sets the packet that is being either sent
     * or received to the specified packet.
     *
     * @param packet The new packet
     * @return This event
     */
    public final Packet<?> setPacket(Packet<?> packet) {
        return this.packet = packet;
    }

    /**
     * @return The packet direction, send or receive
     */
    public final Type getType() {
        return this.type;
    }

    /**
     * Indicates which direction the packet
     * is coming from, either being sent or
     * received.
     */
    public enum Type {
        SEND, RECEIVE
    }
}
