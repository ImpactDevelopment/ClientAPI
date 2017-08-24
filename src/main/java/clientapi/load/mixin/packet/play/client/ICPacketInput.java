package clientapi.load.mixin.packet.play.client;

import net.minecraft.network.play.client.CPacketInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 8:49 PM
 */
@Mixin(CPacketInput.class)
public interface ICPacketInput {

    @Accessor float getStrafeSpeed();

    @Accessor void setStrafeSpeed(float strafeSpeed);

    @Accessor float getForwardSpeed();

    @Accessor void getForwardSpeed(float forwardSpeed);

    @Accessor boolean isJumping();

    @Accessor void setJumping(boolean jumping);

    @Accessor boolean isSneaking();

    @Accessor void setSneaking(boolean sneaking);
}
