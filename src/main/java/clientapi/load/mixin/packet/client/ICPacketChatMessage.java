package clientapi.load.mixin.packet.client;

import net.minecraft.network.play.client.CPacketChatMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:02 PM
 */
@Mixin(CPacketChatMessage.class)
public interface ICPacketChatMessage {

    @Accessor void setMessage(String message);

    @Accessor String getMessage();
}
