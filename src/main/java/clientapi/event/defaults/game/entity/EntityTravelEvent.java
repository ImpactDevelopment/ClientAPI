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

package clientapi.event.defaults.game.entity;

import me.zero.alpine.type.Cancellable;
import me.zero.alpine.type.EventState;
import net.minecraft.entity.EntityLivingBase;

/**
 * Called before and after {@code EntityLivingBase#travel(float, float, float)}
 * with respective states. The main purpose of the event is to allow the developer
 * to control the movement of any entities that are instances of {@code EntityPlayerSP}.
 * This would include the local player and bots. Cancelling is only effective in the
 * {@code PRE} state, and prevents the entity from "travelling".
 *
 * @author Brady
 * @since 8/16/2017 6:10 PM
 */
public final class EntityTravelEvent extends Cancellable {

    /**
     * State of when the event was called relative to the invokation
     * of {@code EntityLivingBase#travel(float, float, float)}
     */
    private final EventState state;

    /**
     * The {@code Entity} that is "travelling"
     */
    private final EntityLivingBase entity;

    /**
     * Strafe travel amount
     */
    private float strafe;

    /**
     * Vertical travel amount
     */
    private float vertical;

    /**
     * Forward travel amount
     */
    private float forward;

    public EntityTravelEvent(EventState state, EntityLivingBase entity, float strafe, float vertical, float forward) {
        this.entity = entity;
        this.state = state;
        this.strafe = strafe;
        this.vertical = vertical;
        this.forward = forward;
    }

    /**
     * Sets the strafe travel amount to be passed down to {@code EntityLivingBase#travel(float, float, float)}
     * @param strafe New strafe travel amount
     * @return This event
     */
    public final EntityTravelEvent setStrafe(float strafe) {
        this.strafe = strafe;
        return this;
    }

    /**
     * Sets the vertical travel amount to be passed down to {@code EntityLivingBase#travel(float, float, float)}
     * @param vertical New strafe travel amount
     * @return This event
     */
    public final EntityTravelEvent setVertical(float vertical) {
        this.vertical = vertical;
        return this;
    }

    /**
     * Sets the forward travel amount to be passed down to {@code EntityLivingBase#travel(float, float, float)}
     * @param forward New forward travel amount
     * @return This event
     */
    public final EntityTravelEvent setForward(float forward) {
        this.forward = forward;
        return this;
    }

    /**
     * @return The strafe travel amount
     */
    public final float getStrafe() {
        return this.strafe;
    }

    /**
     * @return The vertical travel amount
     */
    public final float getVertical() {
        return this.vertical;
    }

    /**
     * @return The forward travel amount
     */
    public final float getForward() {
        return this.forward;
    }

    /**
     * @return The state of this event
     */
    public final EventState getState() {
        return this.state;
    }

    /**
     * @return The entity "travelling"
     */
    public final EntityLivingBase getEntity() {
        return this.entity;
    }

    @Override
    public String toString() {
        return "EntityTravelEvent{" +
                "state=" + state +
                ", entity=" + entity +
                ", strafe=" + strafe +
                ", vertical=" + vertical +
                ", forward=" + forward +
                '}';
    }
}
