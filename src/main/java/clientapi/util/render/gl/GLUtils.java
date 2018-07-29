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

package clientapi.util.render.gl;

import clientapi.ClientAPI;
import clientapi.event.defaults.game.render.RenderWorldEvent;
import clientapi.util.interfaces.Helper;
import clientapi.util.math.Vec3;
import clientapi.util.render.Colors;
import clientapi.util.render.gl.glu.Project;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * OpenGL utils
 *
 * @author Brady
 * @since 2/20/2017 12:00 PM
 */
public final class GLUtils implements Helper {

    private GLUtils() {}

    private static final FloatBuffer MODELVIEW = BufferUtils.createFloatBuffer(16);
    private static final FloatBuffer PROJECTION = BufferUtils.createFloatBuffer(16);
    private static final IntBuffer VIEWPORT = BufferUtils.createIntBuffer(16);
    private static final FloatBuffer TO_SCREEN_BUFFER = BufferUtils.createFloatBuffer(3);
    private static final FloatBuffer TO_WORLD_BUFFER = BufferUtils.createFloatBuffer(3);

    // Calls clinit
    public static void init() {}

    static {
        ClientAPI.EVENT_BUS.subscribe(new Object() {

            @EventHandler
            private final Listener<RenderWorldEvent> render3DListener = new Listener<>(event -> {
                GlStateManager.getFloat(GL_MODELVIEW_MATRIX, (FloatBuffer) MODELVIEW.clear());
                GlStateManager.getFloat(GL_PROJECTION_MATRIX, (FloatBuffer) PROJECTION.clear());
                GL11.glGetIntegerv(GL_VIEWPORT, (IntBuffer) VIEWPORT.clear());
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
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(angle, 1, 0, 0);
        GlStateManager.translate(-x, -y, -z);
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
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(angle, 0, 1, 0);
        GlStateManager.translate(-x, -y, -z);
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
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(angle, 0, 0, 1);
        GlStateManager.translate(-x, -y, -z);
    }

    /**
     * Converts a Vec3 to its screen projection
     *
     * @see GLUtils#toScreen(double, double, double)
     *
     * @return Screen projected coordinates
     */
    public static Vec3 toScreen(Vec3 pos) {
        return GLUtils.toScreen(pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * Projects the specified XYZ world position to a
     * 2D position representing as screen coordinates.
     * Projected positions that are outside of the
     * viewing frustum can be calculated by checking
     * the returned Z value.
     *
     * @return Screen projected coordinates
     */
    public static Vec3 toScreen(double x, double y, double z) {
        boolean result = Project.gluProject((float) x, (float) y, (float) z, MODELVIEW, PROJECTION, VIEWPORT, (FloatBuffer) TO_SCREEN_BUFFER.clear());
        if (result) {
            return new Vec3(TO_SCREEN_BUFFER.get(0), mc.mainWindow.getWindowHeight() - TO_SCREEN_BUFFER.get(1), TO_SCREEN_BUFFER.get(2));
        }
        return null;
    }

    /**
     * Converts a Vec3 to its world projection
     *
     * @see GLUtils#toWorld(double, double, double)
     *
     * @return World projected coordinates
     */
    public static Vec3 toWorld(Vec3 pos) {
        return GLUtils.toWorld(pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * Projects the specified XYZ screen position to
     * a 3D position representing a world position. Can
     * be used to calculate what object the mouse over in
     * the 3D game world using raytracing.
     *
     * @return World projected coordinates
     */
    public static Vec3 toWorld(double x, double y, double z) {
        boolean result = Project.gluUnProject((float) x, (float) y, (float) z, MODELVIEW, PROJECTION, VIEWPORT, (FloatBuffer) TO_WORLD_BUFFER.clear());
        if (result) {
            return new Vec3(TO_WORLD_BUFFER.get(0), TO_WORLD_BUFFER.get(1), TO_WORLD_BUFFER.get(2));
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
