package clientapi.load.mixin.packet.status.server;

import net.minecraft.network.status.server.SPacketPong;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/24/2017 9:23 PM
 */
@Mixin(SPacketPong.class)
public interface ISPacketPong {

    @Accessor long getClientTime();

    @Accessor void setClientTime(long clientTime);
}
