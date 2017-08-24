package clientapi.load.mixin.packet.login.server;

import net.minecraft.network.login.server.SPacketEnableCompression;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/24/2017 7:38 AM
 */
@Mixin(SPacketEnableCompression.class)
public interface ISPacketEnableCompression {

    @Accessor int getCompressionThreshold();

    @Accessor void setCompressionThreshold(int compressionThreshold);
}
