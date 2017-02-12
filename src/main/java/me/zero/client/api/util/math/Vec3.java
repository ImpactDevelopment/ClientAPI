package me.zero.client.api.util.math;

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
 * Created by Brady on 2/12/2017.
 */
public class Vec3 {

    private float x, y, z;

    public Vec3(double x, double y, double z) {
        this((float) x, (float) y, (float) z);
    }

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 x(float x) {
        this.x = x;
        return this;
    }

    public Vec3 y(float y) {
        this.y = y;
        return this;
    }

    public Vec3 z(float z) {
        this.z = z;
        return this;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    public Vec3 add(Vec3 vector) {
        return new Vec3(this.x + vector.x, this.y + vector.y, this.z + vector.z);
    }

    public Vec3 add(double x, double y, double z) {
        return add(new Vec3(x, y, z));
    }

    public Vec3 add(float x, float y, float z) {
        return add(new Vec3(x, y, z));
    }

    public Vec3 sub(Vec3 vector) {
        return new Vec3(this.x - vector.x, this.y - vector.y, this.z - vector.z);
    }

    public Vec3 sub(double x, double y, double z) {
        return sub(new Vec3(x, y, z));
    }

    public Vec3 sub(float x, float y, float z) {
        return sub(new Vec3(x, y, z));
    }

    public Vec3 scale(float scale) {
        return new Vec3(this.x * scale, this.y * scale, this.z * scale);
    }

    public Vec2 rotationsTo(Vec3 vector) {
        double diffX = vector.x - x;
        double diffY = vector.y - y;
        double diffZ = vector.z - z;
        double hdistance = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float) (-(Math.atan2(diffY, hdistance) * 180.0D / Math.PI));
        return new Vec2(yaw, pitch);
    }

    public Vec3 toScreen() {
        FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
        IntBuffer viewport = BufferUtils.createIntBuffer(16);
        FloatBuffer modelView = BufferUtils.createFloatBuffer(16);
        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        glGetFloat(GL_MODELVIEW_MATRIX, modelView);
        glGetFloat(GL_PROJECTION_MATRIX, projection);
        glGetInteger(GL_VIEWPORT, viewport);
        boolean result = GLU.gluProject(x, y, z, modelView, projection, viewport, screenCoords);
        if (result) {
            return new Vec3(screenCoords.get(0), Display.getHeight() - screenCoords.get(1), screenCoords.get(2));
        }
        return null;
    }
}
