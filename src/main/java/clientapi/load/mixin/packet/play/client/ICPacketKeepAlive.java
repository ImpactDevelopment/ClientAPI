package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketKeepAlive;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:53 PM
 */
@Mixin(CPacketKeepAlive.class)
public interface ICPacketKeepAlive {

    @Accessor int getKey();

    @Accessor void setKey(int key);
}
