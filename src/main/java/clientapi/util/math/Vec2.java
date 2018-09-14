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

package clientapi.util.math;

import clientapi.util.render.gl.GLUtils;

/**
 * A Vec with an X and Y position
 *
 * @author Brady
 * @since 2/12/2017
 */
public final class Vec2 {

    /**
     * Coordinates of this Vec2
     */
    private float x, y;

    public Vec2() {
        this(0, 0);
    }

    public Vec2(Vec2 vec) {
        this(vec.x, vec.y);
    }

    public Vec2(double x, double y) {
        this((float) x, (float) y);
    }

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the Vec2 X value
     *
     * @param x The new X value
     * @return This Vec2
     */
    public final Vec2 setX(float x) {
        this.x = x;
        return this;
    }

    /**
     * Sets the Vec2 Y value
     *
     * @param y The new Y value
     * @return This Vec2
     */
    public final Vec2 setY(float y) {
        this.y = y;
        return this;
    }

    /**
     * @return The Vec2 x value
     */
    public final float getX() {
        return this.x;
    }

    /**
     * @return The Vec2 y value
     */
    public final float getY() {
        return this.y;
    }

    /**
     * Adds the X and Y of one Vec2 to this Vec2
     *
     * @param vec Vec2 being added
     * @return The new Vec2
     */
    public final Vec2 add(Vec2 vec) {
        return new Vec2(this.x + vec.x, this.y + vec.y);
    }

    /**
     * Adds the specified X and Y to this Vec2
     *
     * @param x X value being added
     * @param y Y value being added
     * @return The new Vec2
     */
    public final Vec2 add(double x, double y) {
        return add(new Vec2(x, y));
    }

    /**
     * Adds the specified X and Y to this Vec2
     *
     * @param x X value being added
     * @param y Y value being added
     * @return The new Vec2
     */
    public final Vec2 add(float x, float y) {
        return add(new Vec2(x, y));
    }

    /**
     * Subtracts the X and Y of one Vec3 from this Vec2
     *
     * @param vec Vec2 being subtracted by
     * @return The new Vec2
     */
    public final Vec2 sub(Vec2 vec) {
        return new Vec2(this.x - vec.x, this.y - vec.y);
    }

    /**
     * Subtracts the specified X and Y from this Vec2
     *
     * @param x X value being subtracted
     * @param y Y value being subtracted
     * @return The new Vec2
     */
    public final Vec2 sub(double x, double y) {
        return sub(new Vec2(x, y));
    }

    /**
     * Subtracts the specified X and Y from this Vec2
     *
     * @param x X value being subtracted
     * @param y Y value being subtracted
     * @return The new Vec2
     */
    public final Vec2 sub(float x, float y) {
        return sub(new Vec2(x, y));
    }

    /**
     * Multiplies the X and Y of this Vec2 by a scale
     *
     * @param scale The scale
     * @return The new Vec2
     */
    public final Vec2 scale(float scale) {
        return new Vec2(this.x * scale, this.y * scale);
    }

    /**
     * Creates a Vec3 from this Vec2
     *
     * @return This Vec2 as a Vec3
     */
    public final Vec3 toVec3() {
        return new Vec3(x, y, 0);
    }

    /**
     * Creates a new Vec2 with the same X/Y as this Vec2
     *
     * @return the new Vec2
     */
    public final Vec2 copy() {
        return new Vec2(this);
    }

    /**
     * Transfers the X/Y from another Vec2 and sets this
     * Vec2's X/Y to it
     *
     * @param vec Another Vec2
     *
     * @return This Vec2
     */
    public final Vec2 transfer(Vec2 vec) {
        this.x = vec.x;
        this.y = vec.y;
        return this;
    }

    /**
     * Calculates the distance to another Vec2
     *
     * @param vec Another Vec2
     *
     * @return The distance
     */
    public final float distanceTo(Vec2 vec) {
        double dx = x - vec.x;
        double dy = y - vec.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns the world projected coordinates of this Vec3
     *
     * @return World projected Coordinates as a Vec3
     */
    public final Vec3 toScreen() {
        return GLUtils.toWorld(this.toVec3());
    }

    @Override
    public String toString() {
        return "Vec2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
