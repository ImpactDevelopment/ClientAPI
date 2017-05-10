package me.zero.client.api.util.render;

import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import pw.knx.feather.tessellate.GrowingTess;
import pw.knx.feather.tessellate.OffsetTess;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * The basic render utils for any client,
 *
 * @since 1.0
 *
 * @author Brady
 * @since 2/4/2017 12:00 PM
 */
public final class RenderUtils {

    private RenderUtils() {}

    /**
     * Instance of the Tessellator
     */
    public static final OffsetTess tessellator = new OffsetTess(new GrowingTess(4));

    /**
     * Stores ClientState Gl Caps when setting up
     */
    private static final List<Integer> csBuffer = new ArrayList<>();

    /**
     * Called before rendering. Enables blending,
     * line smoothing, disables 2D texturing,
     * depth and the depth mask.
     *
     * @since 1.0
     *
     * @param start true if we are starting to render, false if we are finishing
     */
    public static void setupRender(boolean start) {
        if (start) {
            GlStateManager.enableBlend();
            glEnable(GL_LINE_SMOOTH);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();

            GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        } else {
            GlStateManager.disableBlend();
            GlStateManager.enableTexture2D();
            glDisable(GL_LINE_SMOOTH);
            GlStateManager.enableDepth();
        }
        GlStateManager.depthMask(!start);
    }

    /**
     * Enables/Disables the {@code GL_VERTEX_ARRAY} cap
     *
     * @param enabled The new enabled state of {@code GL_VERTEX_ARRAY}
     */
    public static void setupClientState(GlClientState state, boolean enabled) {
        csBuffer.clear();
        if (state.ordinal() > 0)
            csBuffer.add(state.cap);

        csBuffer.add(GL_VERTEX_ARRAY);

        if (enabled) csBuffer.forEach(GlStateManager::glEnableClientState);
        else         csBuffer.forEach(GlStateManager::glDisableClientState);
    }

    /**
     * Draws a line from one Vec2 to another
     *
     * @since 1.0
     *
     * @param start Starting Vec2
     * @param end Ending Vec2
     * @param width Line width
     */
    public static void drawLine(Vec2 start, Vec2 end, float width) {
        drawLine(start.getX(), start.getY(), end.getX(), end.getY(), width);
    }

    /**
     * Draws a line from one Vec3 to another
     *
     * @since 1.0
     *
     * @param start Starting Vec3
     * @param end Ending Vec3
     * @param width Line width
     */
    public static void drawLine(Vec3 start, Vec3 end, float width) {
        drawLine((float) start.getX(), (float) start.getY(), (float) start.getZ(), (float) end.getX(), (float) end.getY(), (float) end.getZ(), width);
    }

    /**
     * Draws a line from one position to another
     *
     * @since 1.0
     *
     * @param x Start X
     * @param y Start Y
     * @param x1 End X
     * @param y1 End Y
     * @param width Line width
     */
    public static void drawLine(float x, float y, float x1, float y1, float width) {
        drawLine(x, y, 0, x1, y1, 0, width);
    }

    /**
     * Draws a line from one position to another
     *
     * @since 1.0
     *
     * @param x Start X
     * @param y Start Y
     * @param z Start X
     * @param x1 End X
     * @param y1 End Y
     * @param z1 End Z
     * @param width Line width
     */
    public static void drawLine(float x, float y, float z, float x1, float y1, float z1, float width) {
        glLineWidth(width);

        setupRender(true);
        setupClientState(GlClientState.VERTEX, true);
        tessellator.vertex(x, y, z).vertex(x1, y1, z1).draw(GL_LINE_STRIP);
        setupClientState(GlClientState.VERTEX, false);
        setupRender(false);
    }

    /**
     * Draws a flipped textured rectangle
     *
     * @since 1.0
     *
     * @param x1 Top corner X of the rectangle
     * @param y1 Top corner Y of the rectangle
     * @param x2 Bottom corner X of the rectangle
     * @param y2 Bottom corner Y of the rectangle
     */
    public static void drawFlippedTexturedRect(float x1, float y1, float x2, float y2) {
        setupClientState(GlClientState.TEXTURE, true);

        tessellator
                .vertex(x1, y2, 0).texture(0, 0)
                .vertex(x2, y2, 0).texture(1, 0)
                .vertex(x2, y1, 0).texture(1, 1)
                .vertex(x1, y1, 0).texture(0, 1)
                .draw(GL_QUADS);

        setupClientState(GlClientState.TEXTURE, false);
    }

    /**
     * Draws a reflected textured rectangle
     *
     * @since 1.0
     *
     * @param x1 Top corner X of the rectangle
     * @param y1 Top corner Y of the rectangle
     * @param x2 Bottom corner X of the rectangle
     * @param y2 Bottom corner Y of the rectangle
     */
    public static void drawReflectedTexturedRect(float x1, float y1, float x2, float y2) {
        setupClientState(GlClientState.TEXTURE, true);

        tessellator
                .vertex(x1, y2, 0).texture(1, 0)
                .vertex(x2, y2, 0).texture(1, 0)
                .vertex(x2, y1, 0).texture(0, 1)
                .vertex(x1, y1, 0).texture(1, 1)
                .draw(GL_QUADS);

        setupClientState(GlClientState.TEXTURE, false);
    }

    /**
     * Renders a rectangle
     *
     * @since 1.0
     *
     * @param x1 Top corner X of the rectangle
     * @param y1 Top corner Y of the rectangle
     * @param x2 Bottom corner X of the rectangle
     * @param y2 Bottom corner Y of the rectangle
     * @param color The color of the rectangle
     */
    public static void rectangle(float x1, float y1, float x2, float y2, int color) {
        GlUtils.glColor(color);

        setupRender(true);
        setupClientState(GlClientState.VERTEX, true);
        tessellator.vertex(x1, y2, 0).vertex(x2, y2, 0).vertex(x2, y1, 0).vertex(x1, y1, 0).draw(GL_QUADS);
        setupClientState(GlClientState.VERTEX, false);
        setupRender(false);
    }

    // Bordered

    public static void rectangleBordered(float x1, float y1, float x2, float y2, int borderColor, int internalColor) {
        rectangleBordered(x1, y1, x2, y2, 0.5F, borderColor, internalColor);
    }

    public static void rectangleBordered(float x1, float y1, float x2, float y2, float width, int borderColor, int internalColor) {
        rectangle(x1 + width, y1 + width, x2 - width, y2 - width, internalColor);
        rectangle(x1 + width, y1, x2 - width, y1 + width, borderColor);
        rectangle(x1, y1, x1 + width, y2, borderColor);
        rectangle(x2 - width, y1, x2, y2, borderColor);
        rectangle(x1 + width, y2 - width, x2 - width, y2, borderColor);
    }

    // Gradient

    public static void rectangleGradient(float x1, float y1, float x2, float y2, int c1, int c2) {
        rectangleGradient(x1, y1, x2, y2, new int[] { c1, c2 });
    }

    private static void rectangleGradient(float x1, float y1, float x2, float y2, int[] color) {
        if (color.length == 0)
            throw new RuntimeException("At least one set of colors should be supplied");

        float[] r = new float[color.length];
        float[] g = new float[color.length];
        float[] b = new float[color.length];
        float[] a = new float[color.length];
        for (int i = 0; i < color.length; i++) {
            float[] c = Colors.getColor(color[i]);
            r[i] = c[0]; g[i] = c[1]; b[i] = c[2]; a[i] = c[3];
        }

        if (color.length == 1) {
            r = new float[] { r[0], r[0], r[0], r[0] };
            g = new float[] { g[0], g[0], g[0], g[0] };
            b = new float[] { b[0], b[0], b[0], b[0] };
            a = new float[] { a[0], a[0], a[0], a[0] };
        } else if (color.length > 1 && color.length < 4) {
            r = new float[] { r[0], r[0], r[1], r[1] };
            g = new float[] { g[0], g[0], g[1], g[1] };
            b = new float[] { b[0], b[0], b[1], b[1] };
            a = new float[] { a[0], a[0], a[1], a[1] };
        } else {
            r = new float[] { r[0], r[1], r[2], r[3] };
            g = new float[] { g[0], g[1], g[2], g[3] };
            b = new float[] { b[0], b[1], b[2], b[3] };
            a = new float[] { a[0], a[1], a[2], a[3] };
        }

        setupRender(true);
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.shadeModel(GL_FLAT);
        setupClientState(GlClientState.COLOR, true);

        tessellator
                .color(r[0], g[0], b[0], a[0]).vertex(x1, y2, 0)
                .color(r[1], g[1], b[1], a[1]).vertex(x2, y2, 0)
                .color(r[2], g[2], b[2], a[2]).vertex(x2, y1, 0)
                .color(r[3], g[3], b[3], a[3]).vertex(x1, y1, 0)
                .draw(GL_QUADS);

        setupClientState(GlClientState.COLOR, false);
        GlStateManager.shadeModel(GL_FLAT);
        setupRender(false);
    }

    // Bordered Gradient

    public static void rectangleBorderedGradient(float x1, float y1, float x2, float y2, int border, int c1, int c2) {
        rectangleBorderedGradient(x1, y1, x2, y2, border, c1, c2, 0.5F);
    }

    public static void rectangleBorderedGradient(float x1, float y1, float x2, float y2, int border, int c1, int c2, float width) {
        rectangleBorderedGradient(x1, y1, x2, y2, new int[] { c1, c2 }, new int[] { border }, width);
    }

    public static void rectangleBorderedGradient(float x1, float y1, float x2, float y2, int[] fill, int[] outline, float width) {
        rectangleOutlinedGradient(x1, y1, x2, y2, outline, width);
        rectangleGradient(x1 + width, y1 + width, x2 - width, y2 - width, fill);
    }

    public static void rectangleOutlinedGradient(float x1, float y1, float x2, float y2, int[] color, float width) {
        rectangleGradient(x1, y1, x2, y1 + width, color);
        rectangleGradient(x1, y2 - width, x2, y2, color);
        rectangleGradient(x1, y1 + width, x1 + width, y2 - width, color);
        rectangleGradient(x2 - width, y1 + width, x2, y2 - width, color);
    }
}
