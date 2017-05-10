package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.EventState;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;
import me.zero.client.wrapper.IEntity;
import net.minecraft.util.math.AxisAlignedBB;

/**
 * Called before and after packets are sent to
 * the server to update your location and position
 *
 * @author Brady
 * @since 2/12/2017 12:00 PM
 */
public final class MotionUpdateEvent implements Helper {

    /**
     * Original and New positions
     */
    private static Vec3 oPos, nPos;

    /**
     * Original and New rotations
     */
    private static Vec2 oRotation, nRotation;

    /**
     * OnGround state
     */
    private static boolean oGround, nGround;

    /**
     * State of this event
     */
    private final EventState type;

    public MotionUpdateEvent(EventState type) {
        this.type = type;
        if (type == EventState.POST) return;

        IEntity util = (IEntity) mc.player;
        oPos      = util.getPos().y(mc.player.getEntityBoundingBox().minY);
        oRotation = util.getRotations();
        oGround   = mc.player.onGround;

        nPos      = util.getPos().y(mc.player.getEntityBoundingBox().minY);
        nRotation = util.getRotations();
        nGround   = mc.player.onGround;
    }

    /**
     * Sets the X position
     *
     * @param x The new X position
     * @return This event
     */
    public final MotionUpdateEvent x(double x) {
        nPos.x(x);
        return this;
    }

    /**
     * Sets the Y position
     *
     * @param y The new Y position
     * @return This event
     */
    public final MotionUpdateEvent y(double y) {
        nPos.y(y);
        return this;
    }

    /**
     * Sets the Z position
     *
     * @param z The new Z position
     * @return This event
     */
    public final MotionUpdateEvent z(double z) {
        nPos.z(z);
        return this;
    }

    /**
     * Sets the Yaw rotation
     *
     * @param yaw The new yaw rotation
     * @return This event
     */
    public final MotionUpdateEvent yaw(float yaw) {
        nRotation.x(yaw);
        return this;
    }

    /**
     * Sets the Pitch rotation
     *
     * @param pitch The new pitch rotation
     * @return This event
     */
    public final MotionUpdateEvent pitch(float pitch) {
        nRotation.y(pitch);
        return this;
    }

    /**
     * Sets the onGround state
     *
     * @param onGround The new onGround state
     * @return This event
     */
    public final MotionUpdateEvent onGround(boolean onGround) {
        nGround = onGround;
        return this;
    }

    /**
     * @return The X position
     */
    public final double getX() {
        return nPos.getX();
    }

    /**
     * @return The Y position
     */
    public final double getY() {
        return nPos.getY();
    }

    /**
     * @return The Z position
     */
    public final double getZ() {
        return nPos.getZ();
    }

    /**
     * @return The Yaw rotation
     */
    public final float getYaw() {
        return nRotation.getX();
    }

    /**
     * @return The Pitch rotation
     */
    public final float getPitch() {
        return nRotation.getY();
    }

    /**
     * @return The OnGround state
     */
    public final boolean isOnGround() {
        return nGround;
    }

    /**
     * @return The state of the event
     */
    public final EventState getState() {
        return this.type;
    }

    /**
     * Applies the new position/rotations
     */
    public static void apply() {
        IEntity util = (IEntity) mc.player;
        util.setPos(nPos);

        double diff = nPos.getY() - mc.player.getEntityBoundingBox().minY;
        mc.player.setEntityBoundingBox(mc.player.getEntityBoundingBox().offset(0, diff, 0));

        util.setRotations(nRotation);
        mc.player.onGround = nGround;
    }

    /**
     * Resets the original position/rotations
     */
    public static void reset() {
        IEntity util = (IEntity) mc.player;
        util.setPos(oPos);

        double diff = oPos.getY() - mc.player.getEntityBoundingBox().minY;
        mc.player.setEntityBoundingBox(mc.player.getEntityBoundingBox().offset(0, diff, 0));

        util.setRotations(oRotation);
        mc.player.onGround = oGround;
    }
}
