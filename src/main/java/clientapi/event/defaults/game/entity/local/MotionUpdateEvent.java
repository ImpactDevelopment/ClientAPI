/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.event.defaults.game.entity.local;

import clientapi.load.mixin.extension.IEntity;
import clientapi.util.interfaces.Helper;
import clientapi.util.math.Vec2;
import clientapi.util.math.Vec3;
import me.zero.alpine.type.Cancellable;
import me.zero.alpine.type.EventState;

/**
 * Called before and after packets are sent to
 * the server to update your location and position
 *
 * @author Brady
 * @since 2/12/2017 12:00 PM
 */
public final class MotionUpdateEvent extends Cancellable implements Helper {

    /**
     * Position
     */
    private Vec3 pos = new Vec3();

    /**
     * Rotations
     */
    private Vec2 rotations = new Vec2();

    /**
     * OnGround state
     */
    private boolean onGround;

    /**
     * State of this event
     */
    private final EventState type;

    public MotionUpdateEvent(EventState type) {
        this.type = type;
        if (type == EventState.POST)
            return;

        IEntity me = (IEntity) mc.player;
        this.pos.transfer(me.getPos()).setY(mc.player.getEntityBoundingBox().minY);
        this.rotations.transfer(me.getRotations());
        this.onGround = mc.player.onGround;
    }

    /**
     * Sets the X position
     *
     * @param x The new X position
     * @return This event
     */
    public final MotionUpdateEvent setX(double x) {
        this.pos.setX(x);
        return this;
    }

    /**
     * Sets the Y position
     *
     * @param y The new Y position
     * @return This event
     */
    public final MotionUpdateEvent setY(double y) {
        this.pos.setY(y);
        return this;
    }

    /**
     * Sets the Z position
     *
     * @param z The new Z position
     * @return This event
     */
    public final MotionUpdateEvent setZ(double z) {
        this.pos.setZ(z);
        return this;
    }

    /**
     * Sets the Yaw rotation
     *
     * @param yaw The new yaw rotation
     * @return This event
     */
    public final MotionUpdateEvent setYaw(float yaw) {
        this.rotations.setX(yaw);
        return this;
    }

    /**
     * Sets the Pitch rotation
     *
     * @param pitch The new pitch rotation
     * @return This event
     */
    public final MotionUpdateEvent setPitch(float pitch) {
        this.rotations.setY(pitch);
        return this;
    }

    /**
     * Sets the onGround state
     *
     * @param onGround The new onGround state
     * @return This event
     */
    public final MotionUpdateEvent setOnGround(boolean onGround) {
        this.onGround = onGround;
        return this;
    }

    /**
     * @return The X position
     */
    public final double getX() {
        return this.pos.getX();
    }

    /**
     * @return The Y position
     */
    public final double getY() {
        return this.pos.getY();
    }

    /**
     * @return The Z position
     */
    public final double getZ() {
        return this.pos.getZ();
    }

    /**
     * @return The Yaw rotation
     */
    public final float getYaw() {
        return this.rotations.getX();
    }

    /**
     * @return The Pitch rotation
     */
    public final float getPitch() {
        return this.rotations.getY();
    }

    /**
     * @return The OnGround state
     */
    public final boolean isOnGround() {
        return this.onGround;
    }

    /**
     * @return The state of the event
     */
    public final EventState getState() {
        return this.type;
    }

    @Override
    public String toString() {
        return "MotionUpdateEvent{" +
                "pos=" + pos +
                ", rotations=" + rotations +
                ", onGround=" + onGround +
                ", type=" + type +
                '}';
    }
}
