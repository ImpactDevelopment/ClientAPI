package me.zero.client.api.event.defaults;

import net.minecraft.entity.MoverType;

/**
 * Called when the Client player is moved.
 *
 * @see net.minecraft.entity.Entity#move(net.minecraft.entity.MoverType, double, double, double)
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
 */
public final class MoveEvent {

    /**
     * The type of mover
     */
    private final MoverType type;

    /**
     * X, Y, and Z motion values
     */
    private double x, y, z;

    public MoveEvent(MoverType type, double x, double y, double z) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @since 1.0
     *
     * @return The mover type
     */
    public final MoverType getType() {
        return this.type;
    }

    /**
     * Sets the X motion value
     *
     * @since 1.0
     *
     * @param x New X motion
     * @return This event
     */
    public final MoveEvent x(double x) {
        this.x = x;
        return this;
    }

    /**
     * Sets the Y motion value
     *
     * @since 1.0
     *
     * @param y New Y motion
     * @return This event
     */
    public final MoveEvent y(double y) {
        this.y = y;
        return this;
    }


    /**
     * Sets the Z motion value
     *
     * @since 1.0
     *
     * @param z New Z motion
     * @return This event
     */
    public final MoveEvent z(double z) {
        this.z = z;
        return this;
    }

    /**
     * @since 1.0
     *
     * @return The X motion
     */
    public final double getX() {
        return this.x;
    }

    /**
     * @since 1.0
     *
     * @return The Y motion
     */
    public final double getY() {
        return this.y;
    }

    /**
     * @since 1.0
     *
     * @return The Z motion
     */
    public final double getZ() {
        return this.z;
    }
}
