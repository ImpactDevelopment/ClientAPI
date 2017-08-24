package clientapi.load.mixin.packet.login.server;

import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/24/2017 7:37 AM
 */
@Mixin(SPacketDisconnect.class)
public interface ISPacketDisconnect {

    @Accessor ITextComponent getReason();

    @Accessor void setReason(ITextComponent reason);
}
