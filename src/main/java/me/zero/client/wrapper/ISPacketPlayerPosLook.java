package me.zero.client.wrapper;

/**
 * Used to set the Server Player Packet fields
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/28/2017 12:00 PM
 */
public interface ISPacketPlayerPosLook {

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
}
