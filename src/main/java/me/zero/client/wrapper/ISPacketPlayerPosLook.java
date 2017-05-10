package me.zero.client.wrapper;

/**
 * Used to set the Server Player Packet fields
 *
 * @author Brady
 * @since 2/28/2017 12:00 PM
 */
public interface ISPacketPlayerPosLook {

    /**
     * Sets the packet X coordinate
     *
     * @param x X coordinate
     */
    void setX(double x);

    /**
     * Sets the packet Y coordinate
     *
     * @param y Y coordinate
     */
    void setY(double y);

    /**
     * Sets the packet Z coordinate
     *
     * @param z Z coordinate
     */
    void setZ(double z);

    /**
     * Sets the packet yaw rotation
     *
     * @param yaw Yaw rotation
     */
    void setYaw(float yaw);

    /**
     * Sets the packet pitch rotation
     *
     * @param pitch Pitch rotation
     */
    void setPitch(float pitch);
}
