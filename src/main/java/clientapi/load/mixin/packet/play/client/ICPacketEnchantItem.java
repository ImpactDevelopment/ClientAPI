package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketEnchantItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:38 PM
 */
@Mixin(CPacketEnchantItem.class)
public interface ICPacketEnchantItem {

    @Accessor int getWindowId();

    @Accessor void setWindowId(int windowId);

    @Accessor int getButton();

    @Accessor void setButton(int button);
}
