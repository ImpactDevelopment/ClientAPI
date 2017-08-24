package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:12 PM
 */
@Mixin(CPacketPlayerTryUseItem.class)
public interface ICPacketPlayerTryUseItem {

    @Accessor EnumHand getHand();

    @Accessor void setHand(EnumHand hand);
}
