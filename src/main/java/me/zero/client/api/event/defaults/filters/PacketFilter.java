package me.zero.client.api.event.defaults.filters;

import me.zero.client.api.event.defaults.PacketEvent;
import net.minecraft.network.Packet;

import java.util.function.Predicate;

/**
 * Basic filter for packets
 *
 * @author Brady
 * @since 3/2/2017 12:00 PM
 */
public final class PacketFilter implements Predicate<PacketEvent> {

    /**
     * Packets allowed by this filter
     */
    private final Class<? extends Packet<?>>[] packets;

    @SafeVarargs
    public PacketFilter(Class<? extends Packet<?>>... packets) {
        this.packets = packets;
    }

    @Override
    public boolean test(PacketEvent packetEvent) {
        for (Class<? extends Packet<?>> packet : packets)
            if (packet.isAssignableFrom(packetEvent.getPacket().getClass()))
                return true;

        return false;
    }
}
