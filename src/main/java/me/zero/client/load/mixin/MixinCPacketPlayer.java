package me.zero.client.load.mixin;

import me.zero.client.wrapper.ICPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by Brady on 4/27/2017.
 */
@Mixin(CPacketPlayer.class)
public class MixinCPacketPlayer implements ICPacketPlayer {

    @Shadow private double x;
    @Shadow private double y;
    @Shadow private double z;
    @Shadow private float yaw;
    @Shadow private float pitch;
    @Shadow private boolean onGround;

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }
}
