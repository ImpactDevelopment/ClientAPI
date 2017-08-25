package clientapi.event.defaults.game.network;

import me.zero.alpine.type.Cancellable;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.login.server.SPacketEncryptionRequest;

/**
 * Fired when the server we are connecting to requests us to authenticate with Mojang
 *
 * @since 3.0
 */
public final class EncryptionRequestEvent extends Cancellable {

    private final NetHandlerLoginClient net;
    private final SPacketEncryptionRequest packet;

    /**
     * @param hetHandler the NetHandlerLoginClient object that received the request
     * @param packet the encryption request packet
     */
    public EncryptionRequestEvent(NetHandlerLoginClient hetHandler, SPacketEncryptionRequest packet) {
        this.net = hetHandler;
        this.packet = packet;
    }

    /**
     * @return the NetHandlerLoginClient object that received the request
     */
    public NetHandlerLoginClient getNetHandler() {
        return this.net;
    }

    /**
     * @return the encryption request packet
     */
    public SPacketEncryptionRequest getPacket() {
        return packet;
    }
}
