package clientapi.load.mixin.packet.client;

import net.minecraft.network.play.client.CPacketCloseWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:19 PM
 */
@Mixin(CPacketCloseWindow.class)
public interface ICPacketCloseWindow {

    @Accessor int getWindowId();

    @Accessor void setWindowId(int windowId);
}
