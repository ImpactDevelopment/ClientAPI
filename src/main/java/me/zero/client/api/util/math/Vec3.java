package me.zero.client.api.util.math;

import me.zero.client.api.util.render.GlUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * A vector with a X, Y, and Z position
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/12/2017 12:00 PM
 */
public final class Vec3 {

    private double x, y, z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Sets the Vector X value
     *
     * @since 1.0
     *
     * @param x The new X value
     * @return This Vector
     */
    public final Vec3 x(double x) {
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
    public final Vec3 y(double y) {
        this.y = y;
        return this;
    }

    /**
     * Sets the Vector Z value
     *
     * @since 1.0
     *
     * @param z The new Z value
     * @return This Vector
     */
    public final Vec3 z(double z) {
        this.z = z;
        return this;
    }

    /**
     * @since 1.0
     *
     * @return The vector x value
     */
    public final double getX() {
        return this.x;
    }

    /**
     * @since 1.0
     *
     * @return The vector y value
     */
    public final double getY() {
        return this.y;
    }

    /**
     * @since 1.0
     *
     * @return The vector z value
     */
    public final double getZ() {
        return this.z;
    }

    /**
     * Adds the X, Y and Z of one vector to this vector
     *
     * @since 1.0
     *
     * @param vector Vector being added
     * @return The new vector
     */
    public final Vec3 add(Vec3 vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    /**
     * Adds the specified X, Y and Z to this vector
     *
     * @since 1.0
     *
     * @param x X value being added
     * @param y Y value being added
     * @param z Z value being added
     * @return The new vector
     */
    public final Vec3 add(double x, double y, double z) {
        return new Vec3(this.x + x, this.y + y, this.z + z);
    }

    /**
     * Subtracts the X, Y and Z of one vector from this vector
     *
     * @since 1.0
     *
     * @param vector Vector being added
     * @return The new vector
     */
    public final Vec3 sub(Vec3 vector) {
        return new Vec3(this.x - vector.x, this.y - vector.y, this.z - vector.z);
    }

    /**
     * Subtracts the specified X, Y and Z from this vector
     *
     * @since 1.0
     *
     * @param x X value being subtracted
     * @param y Y value being subtracted
     * @param z Z value being subtracted
     * @return The new vector
     */
    public final Vec3 sub(double x, double y, double z) {
        return new Vec3(this.x - x, this.y - y, this.z - z);
    }

    /**
     * Multiplies the X, Y and Z of this vector by a scale
     *
     * @since 1.0
     *
     * @param scale The scale
     * @return The new vector
     */
    public final Vec3 scale(float scale) {
        return new Vec3(this.x * scale, this.y * scale, this.z * scale);
    }

    /**
     * Calculates the distance to another Vec3
     *
     * @since 1.0
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
     * Determines the rotations from this vector to another vector
     *
     * @since 1.0
     *
     * @param vector The other vector
     * @return The rotations
     */
    public final Vec2 rotationsTo(Vec3 vector) {
        double diffX = vector.x - x;
        double diffY = vector.y - y;
        double diffZ = vector.z - z;
        double hdistance = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float) (-(Math.atan2(diffY, hdistance) * 180.0D / Math.PI));
        return new Vec2(yaw, pitch);
    }

    /**
     * Returns the projected coordinates of this Vector
     *
     * @since 1.0
     *
     * @return Projected Coordinates as a Vec3
     */
    public final Vec3 toScreen() {
        FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
        FloatBuffer modelView = GlUtils.getModelViewMatrix();
        FloatBuffer projection = GlUtils.getProjectionMatrix();
        IntBuffer viewport = GlUtils.getViewport();

        boolean result = GLU.gluProject((float) x, (float) y, (float) z, modelView, projection, viewport, screenCoords);
        if (result) {
            return new Vec3(screenCoords.get(0), Display.getHeight() - screenCoords.get(1), screenCoords.get(2));
        }
        return null;
    }
}
