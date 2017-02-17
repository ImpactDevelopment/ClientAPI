package me.zero.example;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.defaults.PacketEvent;
import me.zero.client.api.util.ReflectionUtils;
import net.minecraft.network.handshake.client.C00Handshake;
import org.apache.logging.log4j.core.net.Protocol;

/**
 * Created by Brady on 2/16/2017.
 */
public class ProtocolPatcher {

    private int protocol = 316;

    public ProtocolPatcher() {
        EventManager.subscribe(this);
    }

    @EventHandler
    private Listener<PacketEvent> packetListener = new Listener<>(event -> {
        if (event.getType() != PacketEvent.Type.SEND)
            return;

        if (event.getPacket() instanceof C00Handshake) {
            C00Handshake handshake = (C00Handshake) event.getPacket();
            ReflectionUtils.setField(handshake, "protocolVersion", protocol);
        }
    });

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }
}
