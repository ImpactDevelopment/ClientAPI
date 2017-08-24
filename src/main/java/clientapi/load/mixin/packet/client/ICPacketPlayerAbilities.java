package clientapi.load.mixin.packet.client;

import net.minecraft.network.play.client.CPacketPlayerAbilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 8/23/2017 9:00 PM
 */
@Mixin(CPacketPlayerAbilities.class)
public interface ICPacketPlayerAbilities {

    @Accessor boolean isInvulnerable();

    @Accessor void setInvulnerable(boolean invulnerable);

    @Accessor boolean isFlying();

    @Accessor void setFlying(boolean flying);

    @Accessor boolean isAllowFlying();

    @Accessor void setAllowFlying(boolean allowFlying);

    @Accessor boolean isCreativeMode();

    @Accessor void setCreativeMode(boolean creativeMode);

    @Accessor float getFlySpeed();

    @Accessor void setFlySpeed(float flySpeed);

    @Accessor float getWalkSpeed();

    @Accessor void setWalkSpeed(float walkSpeed);
}
