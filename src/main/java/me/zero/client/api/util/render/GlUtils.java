package me.zero.client.api.util.render;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.EventManager;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.defaults.Render3DEvent;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;

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

    private static FloatBuffer MODELVIEW_MATRIX, PROJECTION_MATRIX;
    private static IntBuffer VIEWPORT;

    /**
     * Calls clinit
     */
    public static void init() {
        EventManager.subscribe(new Object() {

            @EventHandler
            private final Listener<Render3DEvent> render3DListener = new Listener<>(event -> {
                MODELVIEW_MATRIX = BufferUtils.createFloatBuffer(16);
                glGetFloat(GL_MODELVIEW_MATRIX, MODELVIEW_MATRIX);

                PROJECTION_MATRIX = BufferUtils.createFloatBuffer(16);
                glGetFloat(GL_PROJECTION_MATRIX, PROJECTION_MATRIX);

                VIEWPORT = BufferUtils.createIntBuffer(16);
                glGetInteger(GL_VIEWPORT, VIEWPORT);
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
     * Gets the Model View Matrix as a FloatBuffer
     *
     * @return The Model View Matrix
     */
    public static FloatBuffer getModelViewMatrix() {
        return MODELVIEW_MATRIX;
    }

    /**
     * Gets the Projection Matrix as a FloatBuffer
     *
     * @return The Projection Matrix
     */
    public static FloatBuffer getProjectionMatrix() {
        return PROJECTION_MATRIX;
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
