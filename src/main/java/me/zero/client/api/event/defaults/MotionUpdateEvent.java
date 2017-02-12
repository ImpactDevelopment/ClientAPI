package me.zero.client.api.event.defaults;

import me.zero.client.api.event.type.EventState;
import me.zero.client.api.util.EntityUtil;
import me.zero.client.api.util.interfaces.Helper;
import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;

/**
 * Called before and after packets are sent to
 * the server to update your location and position
 *
 * @since 1.0
 *
 * Created by Brady on 2/12/2017.
 */
public class MotionUpdateEvent implements Helper {

    private EventState type;

    private static Vec3 oPos, nPos;
    private static Vec2 oRotation, nRotation;

    public MotionUpdateEvent(EventState type) {
        this.type = type;
        if (type == EventState.POST) return;

        EntityUtil util = EntityUtil.get(mc.player);
        oPos      = util.getPos();
        oRotation = util.getRotations();
        nPos      = util.getPos();
        nRotation = util.getRotations();
    }

    public MotionUpdateEvent x(double x) {
        nPos.x(x);
        return this;
    }

    public MotionUpdateEvent y(double y) {
        nPos.y(y);
        return this;
    }

    public MotionUpdateEvent z(double z) {
        nPos.z(z);
        return this;
    }

    public MotionUpdateEvent yaw(float yaw) {
        nRotation.x(yaw);
        return this;
    }

    public MotionUpdateEvent pitch(float pitch) {
        nRotation.y(pitch);
        return this;
    }

    public EventState getType() {
        return this.type;
    }

    public static void apply() {
        EntityUtil util = EntityUtil.get(mc.player);
        util.setPos(nPos);
        util.setRotations(nRotation);
    }

    public static void reset() {
        EntityUtil util = EntityUtil.get(mc.player);
        util.setPos(oPos);
        util.setRotations(oRotation);
    }
}
