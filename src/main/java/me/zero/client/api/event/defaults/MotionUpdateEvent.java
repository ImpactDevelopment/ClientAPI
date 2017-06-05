package me.zero.client.api.event.defaults;

import me.zero.alpine.type.EventState;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;
import me.zero.client.load.mixin.wrapper.IEntity;

/**
 * Called before and after packets are sent to
 * the server to update your location and position
 *
 * @author Brady
 * @since 2/12/2017 12:00 PM
 */
public final class MotionUpdateEvent implements Helper {

    /**
     * Position
     */
    private static Vec3 pos = new Vec3();

    /**
     * Rotations
     */
    private static Vec2 rotations = new Vec2();

    /**
     * OnGround state
     */
    private static boolean onGround;

    /**
     * State of this event
     */
    private final EventState type;

    public MotionUpdateEvent(EventState type) {
        this.type = type;
        if (type == EventState.POST)
            return;

        IEntity me = (IEntity) mc.player;
        pos.transfer(me.getPos()).y(mc.player.getEntityBoundingBox().minY);
        rotations.transfer(me.getRotations());
        onGround = mc.player.onGround;
    }

    /**
     * Sets the X position
     *
     * @param x The new X position
     * @return This event
     */
    public final MotionUpdateEvent x(double x) {
        pos.x(x);
        return this;
    }

    /**
     * Sets the Y position
     *
     * @param y The new Y position
     * @return This event
     */
    public final MotionUpdateEvent y(double y) {
        pos.y(y);
        return this;
    }

    /**
     * Sets the Z position
     *
     * @param z The new Z position
     * @return This event
     */
    public final MotionUpdateEvent z(double z) {
        pos.z(z);
        return this;
    }

    /**
     * Sets the Yaw rotation
     *
     * @param yaw The new yaw rotation
     * @return This event
     */
    public final MotionUpdateEvent yaw(float yaw) {
        rotations.x(yaw);
        return this;
    }

    /**
     * Sets the Pitch rotation
     *
     * @param pitch The new pitch rotation
     * @return This event
     */
    public final MotionUpdateEvent pitch(float pitch) {
        rotations.y(pitch);
        return this;
    }

    /**
     * Sets the onGround state
     *
     * @param onGround The new onGround state
     * @return This event
     */
    public final MotionUpdateEvent onGround(boolean onGround) {
        MotionUpdateEvent.onGround = onGround;
        return this;
    }

    /**
     * @return The X position
     */
    public final double getX() {
        return pos.getX();
    }

    /**
     * @return The Y position
     */
    public final double getY() {
        return pos.getY();
    }

    /**
     * @return The Z position
     */
    public final double getZ() {
        return pos.getZ();
    }

    /**
     * @return The Yaw rotation
     */
    public final float getYaw() {
        return rotations.getX();
    }

    /**
     * @return The Pitch rotation
     */
    public final float getPitch() {
        return rotations.getY();
    }

    /**
     * @return The OnGround state
     */
    public final boolean isOnGround() {
        return onGround;
    }

    /**
     * @return The state of the event
     */
    public final EventState getState() {
        return this.type;
    }
}
