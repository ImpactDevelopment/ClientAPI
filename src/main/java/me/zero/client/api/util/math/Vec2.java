package me.zero.client.api.util.math;

import me.zero.client.api.util.render.GlUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Vector with an X and Y position
 *
 * @author Brady
 * @since 2/12/2017 12:00 PM
 */
public final class Vec2 {

    /**
     * Coordinates of this vector
     */
    private float x, y;

    public Vec2() {
        this(0, 0);
    }

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
     * @param x The new X value
     * @return This Vector
     */
    public final Vec2 x(float x) {
        this.x = x;
        return this;
    }

    /**
     * Sets the Vector Y value
     *
     * @param y The new Y value
     * @return This Vector
     */
    public final Vec2 y(float y) {
        this.y = y;
        return this;
    }

    /**
     * @return The vector x value
     */
    public final float getX() {
        return this.x;
    }

    /**
     * @return The vector y value
     */
    public final float getY() {
        return this.y;
    }

    /**
     * Adds the X and Y of one vector to this vector
     *
     * @param vector Vector being added
     * @return The new vector
     */
    public final Vec2 add(Vec2 vector) {
        return new Vec2(this.x + vector.x, this.y + vector.y);
    }

    /**
     * Adds the specified X and Y to this vector
     *
     * @param x X value being added
     * @param y Y value being added
     * @return The new vector
     */
    public final Vec2 add(double x, double y) {
        return add(new Vec2(x, y));
    }

    /**
     * Adds the specified X and Y to this vector
     *
     * @param x X value being added
     * @param y Y value being added
     * @return The new vector
     */
    public final Vec2 add(float x, float y) {
        return add(new Vec2(x, y));
    }

    /**
     * Subtracts the X and Y of one vector from this vector
     *
     * @param vector Vector being subtracted by
     * @return The new vector
     */
    public final Vec2 sub(Vec2 vector) {
        return new Vec2(this.x - vector.x, this.y - vector.y);
    }

    /**
     * Subtracts the specified X and Y from this vector
     *
     * @param x X value being subtracted
     * @param y Y value being subtracted
     * @return The new vector
     */
    public final Vec2 sub(double x, double y) {
        return sub(new Vec2(x, y));
    }

    /**
     * Subtracts the specified X and Y from this vector
     *
     * @param x X value being subtracted
     * @param y Y value being subtracted
     * @return The new vector
     */
    public final Vec2 sub(float x, float y) {
        return sub(new Vec2(x, y));
    }

    /**
     * Multiplies the X and Y of this vector by a scale
     *
     * @param scale The scale
     * @return The new vector
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
     * Calculates the distance to another Vec2
     *
     * @return The distance
     */
    public final float distanceTo(Vec2 vec) {
        double dx = x - vec.x;
        double dy = y - vec.y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Uses GLU#gluUnproject to project the 2D position
     * of this vector to a 3D position in the world.
     *
     * @return World position of this vector
     */
    public final Vec3 toWorld() {
        FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
        FloatBuffer modelView = GlUtils.getModelViewMatrix();
        FloatBuffer projection = GlUtils.getProjectionMatrix();
        IntBuffer viewport = GlUtils.getViewport();

        boolean result = GLU.gluUnProject(x, y, 0, modelView, projection, viewport, screenCoords);
        if (result)
            return new Vec3(screenCoords.get(0), Display.getHeight() - screenCoords.get(1), screenCoords.get(2));

        return null;
    }
}
