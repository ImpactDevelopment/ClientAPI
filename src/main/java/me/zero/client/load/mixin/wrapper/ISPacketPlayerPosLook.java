package me.zero.client.load.mixin.wrapper;

import net.minecraft.network.play.server.SPacketPlayerPosLook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 2/28/2017 12:00 PM
 */
@Mixin(SPacketPlayerPosLook.class)
public interface ISPacketPlayerPosLook {

    @Accessor void setX(double x);

    @Accessor void setY(double y);

    @Accessor void setZ(double z);

    @Accessor void setYaw(float yaw);

    @Accessor void setPitch(float pitch);
}
