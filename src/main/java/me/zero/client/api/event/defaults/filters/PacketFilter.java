package me.zero.client.api.event.defaults.filters;

import me.zero.client.api.event.defaults.PacketEvent;
import net.minecraft.network.Packet;

import java.util.function.Predicate;

/**
 * Basic filter for packets
 *
 * @since 1.0
 *
 * Created by Brady on 3/2/2017.
 */
public class PacketFilter implements Predicate<PacketEvent> {

    private final Class<Packet<?>>[] packets;

    public PacketFilter(Class<Packet<?>>... packets) {
        this.packets = packets;
    }

    @Override
    public boolean test(PacketEvent packetEvent) {
        for (Class<Packet<?>> packet : packets)
            if (packetEvent.getPacket().getClass() == packet)
                return true;

        return false;
    }
}
