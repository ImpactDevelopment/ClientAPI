package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketConfirmTransaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:24 PM
 */
@Mixin(CPacketConfirmTransaction.class)
public interface ICPacketConfirmTransaction {

    @Accessor int getWindowId();

    @Accessor void setWindowId(int windowId);

    @Accessor short getUid();

    @Accessor void setUid(short uid);

    @Accessor boolean isAccepted();

    @Accessor void setAccepted(boolean accepted);
}
