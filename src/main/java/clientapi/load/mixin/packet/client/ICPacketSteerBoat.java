package clientapi.load.mixin.packet.client;

import net.minecraft.network.play.client.CPacketSteerBoat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:39 PM
 */
@Mixin(CPacketSteerBoat.class)
public interface ICPacketSteerBoat {

    @Accessor boolean isLeft();

    @Accessor void setLeft(boolean left);

    @Accessor boolean isRight();

    @Accessor void setRight(boolean right);
}
