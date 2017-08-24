package clientapi.load.mixin.packet.client;

import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:01 PM
 */
@Mixin(CPacketAnimation.class)
public interface ICPacketAnimation {

    @Accessor void setHand(EnumHand hand);

    @Accessor EnumHand getHand();
}
