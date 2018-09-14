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

package clientapi.event.defaults.game.render;

import me.zero.alpine.type.Cancellable;
import net.minecraft.client.particle.ParticleManager;

/**
 * Called when {@link ParticleManager#spawnEffectParticle} is invoked.
 * If cancelled, the particle is not created and therefore does not
 * get spawned.
 *
 * @author Brady
 * @since 4/10/2018
 */
public final class SpawnParticleEvent extends Cancellable {

    /**
     * The Particle ID of the particle being spawned
     */
    private final int particleID;

    /**
     * The X coordinate of the particle being spawned
     */
    private final double x;

    /**
     * The Y coordinate of the particle being spawned
     */
    private final double y;

    /**
     * The Z coordinate of the particle being spawned
     */
    private final double z;

    /**
     * The X speed of the particle being spawned++++
     */
    private final double speedX;

    /**
     * The Y speed of the particle being spawned
     */
    private final double speedY;

    /**
     * The Z speed of the particle being spawned
     */
    private final double speedZ;

    public SpawnParticleEvent(int particleID, double x, double y, double z, double speedX, double speedY, double speedZ) {
        this.particleID = particleID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
    }

    /**
     * @return The Particle ID of the particle being spawned
     */
    public final int getParticleID() {
        return this.particleID;
    }

    /**
     * @return The X coordinate of the particle being spawned
     */
    public final double getX() {
        return this.x;
    }

    /**
     * @return The Y coordinate of the particle being spawned
     */
    public final double getY() {
        return this.y;
    }

    /**
     * @return The Z coordinate of the particle being spawned
     */
    public final double getZ() {
        return this.z;
    }

    /**
     * @return The X speed of the particle being spawned
     */
    public final double getSpeedX() {
        return this.speedX;
    }

    /**
     * @return The Y speed of the particle being spawned
     */
    public final double getSpeedY() {
        return this.speedY;
    }

    /**
     * @return The Z speed of the particle being spawned
     */
    public final double getSpeedZ() {
        return this.speedZ;
    }

    @Override
    public String toString() {
        return "SpawnParticleEvent{" +
                "particleID=" + particleID +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", speedX=" + speedX +
                ", speedY=" + speedY +
                ", speedZ=" + speedZ +
                '}';
    }
}
