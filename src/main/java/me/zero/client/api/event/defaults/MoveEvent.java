package me.zero.client.api.event.defaults;

import net.minecraft.entity.MoverType;

/**
 * Called when the Client player is moved.
 *
 * @see net.minecraft.entity.Entity#moveEntity(net.minecraft.entity.MoverType, double, double, double)
 *
 * @since 1.0
 *
 * Created by Brady on 2/9/2017.
 */
public final class MoveEvent {

    private final MoverType type;
    private double x, y, z;

    public MoveEvent(MoverType type, double x, double y, double z) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MoverType getType() {
        return this.type;
    }

    public MoveEvent setX(double x) {
        this.x = x;
        return this;
    }

    public double getX() {
        return this.x;
    }

    public MoveEvent setY(double y) {
        this.y = y;
        return this;
    }

    public double getY() {
        return this.y;
    }

    public MoveEvent setZ(double z) {
        this.z = z;
        return this;
    }

    public double getZ() {
        return this.z;
    }
}
