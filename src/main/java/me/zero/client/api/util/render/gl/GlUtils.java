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

package me.zero.client.api.util.render.gl;

import me.zero.client.api.ClientAPI;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import me.zero.client.api.event.defaults.game.Render3DEvent;
import me.zero.client.api.util.math.Vec3;
import me.zero.client.api.util.render.Colors;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * OpenGL utils
 *
 * @author Brady
 * @since 2/20/2017 12:00 PM
 */
public final class GlUtils {

    private GlUtils() {}

    private static final FloatBuffer MODELVIEW;
    private static final FloatBuffer PROJECTION;
    private static final IntBuffer VIEWPORT;

    // Calls clinit
    public static void init() {}

    static {
        MODELVIEW = BufferUtils.createFloatBuffer(16);
        PROJECTION = BufferUtils.createFloatBuffer(16);
        VIEWPORT = BufferUtils.createIntBuffer(16);

        ClientAPI.EVENT_BUS.subscribe(new Object() {

            @EventHandler
            private final Listener<Render3DEvent> render3DListener = new Listener<>(event -> {
                glGetFloat(GL_MODELVIEW_MATRIX, (FloatBuffer) MODELVIEW.clear());
                glGetFloat(GL_PROJECTION_MATRIX, (FloatBuffer) PROJECTION.clear());
                glGetInteger(GL_VIEWPORT, (IntBuffer) VIEWPORT.clear());
            });
        });
    }

    /**
     * Sets the color from a hex value
     *
     * @param hex The hex value
     */
    public static void glColor(int hex) {
        float[] color = Colors.getColor(hex);
        GlStateManager.color(color[0], color[1], color[2], color[3]);
    }

    /**
     * Rotates on the X axis at the X, Y, and Z
     * coordinates that are provided.
     *
     * @param angle The amount being rotated
     * @param x The x position being rotated on
     * @param y The y position being rotated on
     * @param z The z position being rotated on
     */
    public static void rotateX(float angle, double x, double y, double z) {
        glTranslated(x, y, z);
        glRotated(angle, 1, 0, 0);
        glTranslated(-x, -y, -z);
    }

    /**
     * Rotates on the Y axis at the X, Y, and Z
     * coordinates that are provided.
     *
     * @param angle The amount being rotated
     * @param x The x position being rotated on
     * @param y The y position being rotated on
     * @param z The z position being rotated on
     */
    public static void rotateY(float angle, double x, double y, double z) {
        glTranslated(x, y, z);
        glRotated(angle, 0, 1, 0);
        glTranslated(-x, -y, -z);
    }

    /**
     * Rotates on the Z axis at the X, Y, and Z
     * coordinates that are provided.
     *
     * @param angle The amount being rotated
     * @param x The x position being rotated on
     * @param y The y position being rotated on
     * @param z The z position being rotated on
     */
    public static void rotateZ(float angle, double x, double y, double z) {
        glTranslated(x, y, z);
        glRotated(angle, 0, 0, 1);
        glTranslated(-x, -y, -z);
    }

    /**
     * Converts a Vec3 to its screen projection
     *
     * @see GlUtils#toScreen(double, double, double)
     *
     * @return Screen projected coordinates
     */
    public static Vec3 toScreen(Vec3 pos) {
        return GlUtils.toScreen(pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * Converts the specified X, Y, and Z position to
     * the 2D projected position. The returned result
     * is a Vec3 containing the X and Y position, to
     * represent the position on-screen, and a Z position
     * that can be used to indicate whether or not the
     * projected position
     *
     * @return Screen projected coordinates
     */
    public static Vec3 toScreen(double x, double y, double z) {
        FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
        boolean result = GLU.gluProject((float) x, (float) y, (float) z, MODELVIEW, PROJECTION, VIEWPORT, screenCoords);
        if (result) {
            return new Vec3(screenCoords.get(0), Display.getHeight() - screenCoords.get(1), screenCoords.get(2));
        }
        return null;
    }

    /**
     * Converts a Vec3 to its world projection
     *
     * @see GlUtils#toWorld(double, double, double)
     *
     * @return World projected coordinates
     */
    public static Vec3 toWorld(Vec3 pos) {
        return GlUtils.toWorld(pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * Converts the specified X, Y, and Z screen positions
     * to a position in the world, that through some fancy
     * maths (raytracing) can be used get the actual block
     * position of the conversion
     *
     * @return World projected coordinates
     */
    public static Vec3 toWorld(double x, double y, double z) {
        FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
        boolean result = GLU.gluUnProject((float) x, (float) y, (float) z, MODELVIEW, PROJECTION, VIEWPORT, screenCoords);
        if (result) {
            return new Vec3(screenCoords.get(0), Display.getHeight() - screenCoords.get(1), screenCoords.get(2));
        }
        return null;
    }

    /**
     * Gets the Model View Matrix as a FloatBuffer
     *
     * @return The Model View Matrix
     */
    public static FloatBuffer getModelview() {
        return MODELVIEW;
    }

    /**
     * Gets the Projection Matrix as a FloatBuffer
     *
     * @return The Projection Matrix
     */
    public static FloatBuffer getProjection() {
        return PROJECTION;
    }

    /**
     * Gets the Render Viewport as an IntegerBuffer
     *
     * @return The Viewport
     */
    public static IntBuffer getViewport() {
        return VIEWPORT;
    }
}
