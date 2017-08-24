package clientapi.load.mixin.packet.handshake.client;

import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.handshake.client.C00Handshake;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 10:21 PM
 */
@Mixin(C00Handshake.class)
public interface IC00Handshake {

    @Accessor int getProtocolVersion();

    @Accessor void setProtocolVersion(int protocolVersion);

    @Accessor String getIp();

    @Accessor void setIp(String ip);

    @Accessor int getPort();

    @Accessor void setPort(int port);

    @Accessor EnumConnectionState getRequestedState();

    @Accessor void setRequestedState(EnumConnectionState requestedState);
}
