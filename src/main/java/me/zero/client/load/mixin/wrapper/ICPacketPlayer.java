package me.zero.client.load.mixin.wrapper;

import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
@Mixin(CPacketPlayer.class)
public interface ICPacketPlayer {

    @Accessor void setX(double x);

    @Accessor double getX();

    @Accessor void setY(double y);

    @Accessor double getY();

    @Accessor void setZ(double z);

    @Accessor double getZ();

    @Accessor void setYaw(float yaw);

    @Accessor float getYaw();

    @Accessor void setPitch(float pitch);

    @Accessor float getPitch();

    @Accessor void setOnGround(boolean onGround);

    @Accessor boolean getOnGround();

    @Accessor void setMoving(boolean moving);

    @Accessor boolean getMoving();

    @Accessor void setRotating(boolean rotating);

    @Accessor boolean getRotating();
}
