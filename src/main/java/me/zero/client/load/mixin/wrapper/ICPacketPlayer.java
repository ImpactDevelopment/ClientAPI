package me.zero.client.load.mixin.wrapper;

import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/**
 * Used to set the Player Packet fields
 *
 * @author Brady
 * @since 2/24/2017 12:00 PM
 */
@Mixin(CPacketPlayer.class)
public interface ICPacketPlayer {

    /**
     * Sets the packet X coordinate
     *
     * @param x X coordinate
     */
    @Accessor void setX(double x);

    /**
     * Gets the packet X coordinate
     *
     * @return X coordinate
     */
    @Accessor double getX();

    /**
     * Sets the packet Y coordinate
     *
     * @param y Y coordinate
     */
    @Accessor void setY(double y);

    /**
     * Gets the packet Y coordinate
     *
     * @return Y coordinate
     */
    @Accessor double getY();

    /**
     * Sets the packet Z coordinate
     *
     * @param z Z coordinate
     */
    @Accessor void setZ(double z);

    /**
     * Gets the packet Z coordinate
     *
     * @return Z coordinate
     */
    @Accessor double getZ();

    /**
     * Sets the packet yaw rotation
     *
     * @param yaw Yaw rotation
     */
    @Accessor void setYaw(float yaw);

    /**
     * Sets the packet yaw rotation
     *
     * @return Yaw rotation
     */
    @Accessor float getYaw();

    /**
     * Sets the packet pitch rotation
     *
     * @param pitch Pitch rotation
     */
    @Accessor void setPitch(float pitch);

    /**
     * Gets the packet pitch rotation
     *
     * @return Pitch rotation
     */
    @Accessor float getPitch();

    /**
     * Sets the packet onGround state
     *
     * @param onGround onGround state
     */
    @Accessor void setOnGround(boolean onGround);

    /**
     * Gets the packet onGround state
     *
     * @return onGround state
     */
    @Accessor boolean getOnGround();

    /**
     * Sets the packet moving state
     *
     * @param moving moving state
     */
    @Accessor void setMoving(boolean moving);

    /**
     * Gets the packet moving state
     *
     * @return moving state
     */
    @Accessor boolean getMoving();

    /**
     * Sets the packet rotating state
     *
     * @param rotating rotating state
     */
    @Accessor void setRotating(boolean rotating);

    /**
     * Gets the packet rotating state
     *
     * @return rotating state
     */
    @Accessor boolean getRotating();

}
