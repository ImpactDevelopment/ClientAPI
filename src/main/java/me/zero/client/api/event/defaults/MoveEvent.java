package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.Cancellable;
import net.minecraft.entity.MoverType;

/**
 * Called when the Client player is moved.
 *
 * @see net.minecraft.entity.Entity#move(net.minecraft.entity.MoverType, double, double, double)
 *
 * @author Brady
 * @since 2/9/2017 12:00 PM
 */
public final class MoveEvent extends Cancellable {

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
     * @return The mover type
     */
    public final MoverType getType() {
        return this.type;
    }

    /**
     * Sets the X motion value
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
     * @param z New Z motion
     * @return This event
     */
    public final MoveEvent z(double z) {
        this.z = z;
        return this;
    }

    /**
     * @return The X motion
     */
    public final double getX() {
        return this.x;
    }

    /**
     * @return The Y motion
     */
    public final double getY() {
        return this.y;
    }

    /**
     * @return The Z motion
     */
    public final double getZ() {
        return this.z;
    }
}
