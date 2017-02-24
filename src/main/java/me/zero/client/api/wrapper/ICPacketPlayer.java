package me.zero.client.api.wrapper;

/**
 * Used to set the Player Packet fields
 *
 * @since 1.0
 *
 * Created by Brady on 2/24/2017.
 */
public interface ICPacketPlayer {

    /**
     * Sets the packet X coordinate
     *
     * @since 1.0
     *
     * @param x X coordinate
     */
    void setX(double x);

    /**
     * Sets the packet Y coordinate
     *
     * @since 1.0
     *
     * @param y Y coordinate
     */
    void setY(double y);

    /**
     * Sets the packet Z coordinate
     *
     * @since 1.0
     *
     * @param z Z coordinate
     */
    void setZ(double z);

    /**
     * Sets the packet yaw rotation
     *
     * @since 1.0
     *
     * @param yaw Yaw rotation
     */
    void setYaw(float yaw);

    /**
     * Sets the packet pitch rotation
     *
     * @since 1.0
     *
     * @param pitch Pitch rotation
     */
    void setPitch(float pitch);

    /**
     * Sets the packet onGround state
     *
     * @since 1.0
     *
     * @param onGround onGround state
     */
    void setOnGround(boolean onGround);
}
