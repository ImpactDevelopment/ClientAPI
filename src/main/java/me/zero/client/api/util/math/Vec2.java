package me.zero.client.api.util.math;

/**
 * Vector with an X and Y position
 *
 * @since 1.0
 *
 * Created by Brady on 2/12/2017.
 */
public class Vec2 {

    /**
     * Coordinates of this vector
     */
    private float x, y;

    public Vec2(double x, double y) {
        this((float) x, (float) y);
    }

    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the Vector X value
     *
     * @since 1.0
     *
     * @param x The new X value
     * @return This Vector
     */
    public Vec2 x(float x) {
        this.x = x;
        return this;
    }

    /**
     * Sets the Vector Y value
     *
     * @since 1.0
     *
     * @param y The new Y value
     * @return This Vector
     */
    public Vec2 y(float y) {
        this.y = y;
        return this;
    }

    /**
     * @since 1.0
     *
     * @return The vector x value
     */
    public float getX() {
        return this.x;
    }

    /**
     * @since 1.0
     *
     * @return The vector y value
     */
    public float getY() {
        return this.y;
    }

    /**
     * Adds the X and Y of one vector to this vector
     *
     * @since 1.0
     *
     * @param vector Vector being added
     * @return The new vector
     */
    public Vec2 add(Vec2 vector) {
        return new Vec2(this.x + vector.x, this.y + vector.y);
    }

    /**
     * Adds the specified X and Y to this vector
     *
     * @since 1.0
     *
     * @param x X value being added
     * @param y Y value being added
     * @return The new vector
     */
    public Vec2 add(double x, double y) {
        return add(new Vec2(x, y));
    }

    /**
     * Adds the specified X and Y to this vector
     *
     * @since 1.0
     *
     * @param x X value being added
     * @param y Y value being added
     * @return The new vector
     */
    public Vec2 add(float x, float y) {
        return add(new Vec2(x, y));
    }

    /**
     * Subtracts the X and Y of one vector from this vector
     *
     * @since 1.0
     *
     * @param vector Vector being subtracted by
     * @return The new vector
     */
    public Vec2 sub(Vec2 vector) {
        return new Vec2(this.x - vector.x, this.y - vector.y);
    }

    /**
     * Subtracts the specified X and Y from this vector
     *
     * @since 1.0
     *
     * @param x X value being subtracted
     * @param y Y value being subtracted
     * @return The new vector
     */
    public Vec2 sub(double x, double y) {
        return add(new Vec2(x, y));
    }

    /**
     * Subtracts the specified X and Y from this vector
     *
     * @since 1.0
     *
     * @param x X value being subtracted
     * @param y Y value being subtracted
     * @return The new vector
     */
    public Vec2 sub(float x, float y) {
        return add(new Vec2(x, y));
    }

    /**
     * Multiplies the X and Y of this vector by a scale
     *
     * @since 1.0
     *
     * @param scale The scale
     * @return The new vector
     */
    public Vec2 scale(float scale) {
        return new Vec2(this.x * scale, this.y * scale);
    }

    /**
     * Creates a Vec3 from this Vec2
     *
     * @since 1.0
     *
     * @return This Vec2 as a Vec3
     */
    public Vec3 toVec3() {
        return new Vec3(x, y, 0);
    }
}
