package clientapi.load.mixin.packet.status.server;

import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.status.server.SPacketServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/24/2017 9:24 PM
 */
@Mixin(SPacketServerInfo.class)
public interface ISPacketServerInfo {

    @Accessor ServerStatusResponse getResponse();

    @Accessor void setResponse(ServerStatusResponse response);
}
