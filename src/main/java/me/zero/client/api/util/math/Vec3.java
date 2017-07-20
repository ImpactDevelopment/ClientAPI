/*
 * Copyright 2017 ZeroMemes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.zero.client.api.util.math;

import me.zero.client.api.util.render.gl.GlUtils;

/**
 * A Vec with a X, Y, and Z position
 *
 * @author Brady
 * @since 2/12/2017 12:00 PM
 */
public final class Vec3 {

    /**
     * Coordinates of this Vec3
     */
    private double x, y, z;

    public Vec3() {
        this(0, 0, 0);
    }

    public Vec3(Vec3 vec) {
        this(vec.x, vec.y, vec.z);
    }

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Sets the Vec3 X value
     *
     * @param x The new X value
     * @return This Vec3
     */
    public final Vec3 x(double x) {
        this.x = x;
        return this;
    }

    /**
     * Sets the Vec3 Y value
     *
     * @param y The new Y value
     * @return This Vec3
     */
    public final Vec3 y(double y) {
        this.y = y;
        return this;
    }

    /**
     * Sets the Vec3 Z value
     *
     * @param z The new Z value
     * @return This Vec3
     */
    public final Vec3 z(double z) {
        this.z = z;
        return this;
    }

    /**
     * @return The Vec3 x value
     */
    public final double getX() {
        return this.x;
    }

    /**
     * @return The Vec3 y value
     */
    public final double getY() {
        return this.y;
    }

    /**
     * @return The Vec3 z value
     */
    public final double getZ() {
        return this.z;
    }

    /**
     * Adds the X, Y and Z of one Vec3 to this Vec3
     *
     * @param vec Vec3 being added
     * @return The new Vec3
     */
    public final Vec3 add(Vec3 vec) {
        return this.add(vec.x, vec.y, vec.z);
    }

    /**
     * Adds the specified X, Y and Z to this Vec3
     *
     * @param x X value being added
     * @param y Y value being added
     * @param z Z value being added
     * @return The new Vec3
     */
    public final Vec3 add(double x, double y, double z) {
        return new Vec3(this.x + x, this.y + y, this.z + z);
    }

    /**
     * Subtracts the X, Y and Z of one Vec3 from this Vec3
     *
     * @param vec Vec3 being added
     * @return The new Vec3
     */
    public final Vec3 sub(Vec3 vec) {
        return new Vec3(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }

    /**
     * Subtracts the specified X, Y and Z from this Vec3
     *
     * @param x X value being subtracted
     * @param y Y value being subtracted
     * @param z Z value being subtracted
     * @return The new Vec3
     */
    public final Vec3 sub(double x, double y, double z) {
        return new Vec3(this.x - x, this.y - y, this.z - z);
    }

    /**
     * Multiplies the X, Y and Z of this Vec3 by a scale
     *
     * @param scale The scale
     * @return The new vec
     */
    public final Vec3 scale(float scale) {
        return new Vec3(this.x * scale, this.y * scale, this.z * scale);
    }

    /**
     * Creates a new Vec3 with the same X/Y/Z as this Vec3
     *
     * @return the new Vec3
     */
    public final Vec3 copy() {
        return new Vec3(this);
    }

    /**
     * Transfers the X/Y/Z from another Vec3 and sets this
     * Vec3's X/Y/Z to it
     *
     * @return This Vec3
     */
    public final Vec3 transfer(Vec3 vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
        return this;
    }

    /**
     * Calculates the distance to another Vec3
     *
     * @return The distance
     */
    public final double distanceTo(Vec3 vec) {
        double dx = x - vec.x;
        double dy = y - vec.y;
        double dz = z - vec.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Calculates the angles that an entity at this
     * position would have to look at to look at the
     * specified Vec3.
     *
     * @param vec The other Vec3
     * @return The rotations
     */
    public final Vec2 rotationsTo(Vec3 vec) {
        double[] diff = { vec.x - x, vec.y - y, vec.z - z };
        double hDist = Math.sqrt(diff[0] * diff[0] + diff[2] * diff[2]);
        return new Vec2(
                Math.toDegrees(Math.atan2(diff[2], diff[0])) - 90.0F,
                -Math.toDegrees(Math.atan2(diff[1], hDist))
        );
    }

    /**
     * Returns the screen projected coordinates of this Vec3
     *
     * @return Screen projected Coordinates as a Vec3
     */
    public final Vec3 toScreen() {
        return GlUtils.toScreen(this);
    }
}
