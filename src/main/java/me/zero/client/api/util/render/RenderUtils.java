package me.zero.client.api.util.render;

import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * The basic render utils for any client
 *
 * @since 1.0
 *
 * Created by Brady on 2/4/2017.
 */
public final class RenderUtils {

    private RenderUtils() {}

    /**
     * Instance of the Tessellator
     */
    private static Tessellator tessellator = Tessellator.getInstance();

    /**
     * Instance of the VertexBuffer
     */
    private static VertexBuffer renderer = tessellator.getBuffer();

    /**
     * Draws a flipped textured rectangle
     *
     * @since 1.0
     *
     * @param x Top corner X of the rectangle
     * @param y Top corner Y of the rectangle
     * @param x1 Bottom corner X of the rectangle
     * @param y1 Bottom corner Y of the rectangle
     */
    public static void drawFlippedTexturedRect(double x, double y, double x1, double y1) {
        renderer.begin(GL_QUADS, POSITION_TEX);
        renderer.pos(x, y1, 0).tex(0, 0).endVertex();
        renderer.pos(x1, y1, 0).tex(1, 0).endVertex();
        renderer.pos(x1, y, 0).tex(1, 1).endVertex();
        renderer.pos(x, y, 0).tex(0, 1).endVertex();
        tessellator.draw();
    }

    /**
     * Draws a reflected textured rectangle
     *
     * @since 1.0
     *
     * @param x Top corner X of the rectangle
     * @param y Top corner Y of the rectangle
     * @param x1 Bottom corner X of the rectangle
     * @param y1 Bottom corner Y of the rectangle
     */
    public static void drawReflectedTexturedRect(double x, double y, double x1, double y1) {
        renderer.begin(GL_QUADS, POSITION_TEX);
        renderer.pos(x, y1, 0).tex(1, 0).endVertex();
        renderer.pos(x1, y1, 0).tex(0, 0).endVertex();
        renderer.pos(x1, y, 0).tex(0, 1).endVertex();
        renderer.pos(x, y, 0).tex(1, 1).endVertex();
        tessellator.draw();
    }

    /**
     * Draws a Textured Rectangle
     *
     * @since 1.0
     *
     * @param x Rect X
     * @param y Rect Y
     * @param width Rect Width
     * @param height Rect Height
     * @param u Tex U
     * @param v Tex V
     * @param t Tex T
     * @param s Tex S
     */
    public static void drawTextureRect(float x, float y, float width, float height, float u, float v, float t, float s) {
        renderer.begin(GL_QUADS, POSITION_TEX);
        renderer.pos(x + width, y, 0F).tex(t, v).endVertex();
        renderer.pos(x, y, 0F).tex(u, v).endVertex();
        renderer.pos(x, y + height, 0F).tex(u, s).endVertex();
        renderer.pos(x + width, y + height, 0F).tex(t, s).endVertex();
        tessellator.draw();
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
        drawLine(start.getX(), start.getY(), start.getZ(), end.getX(), end.getY(), end.getZ(), width);
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
    public static void drawLine(double x, double y, double x1, double y1, float width) {
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
    public static void drawLine(double x, double y, double z, double x1, double y1, double z1, float width) {
        preRender();
        glLineWidth(width);
        renderer.begin(GL_LINE_STRIP, POSITION_COLOR);
        renderer.pos(x, y, z).endVertex();
        renderer.pos(x1, y1, z1).endVertex();
        tessellator.draw();
        postRender();
    }

    /**
     * Called before rendering
     */
    public static void preRender() {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableTexture2D();
        glEnable(GL_LINE_SMOOTH);
        glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        GlStateManager.disableDepth();
        GlStateManager.depthMask(false);
    }

    /**
     * Called after rendering
     */
    public static void postRender() {
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
        glDisable(GL_LINE_SMOOTH);
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
    }

    // Normal

    public static void rectangle(double x1, double y1, double x2, double y2, int color) {
        if (x1 < x2) {
            double var5 = x1;
            x1 = x2;
            x2 = var5;
        }
        if (y1 < y2) {
            double var5 = y1;
            y1 = y2;
            y2 = var5;
        }
        float r = (color >> 16 & 0xFF) / 255.0F;
        float g = (color >> 8 & 0xFF) / 255.0F;
        float b = (color & 0xFF) / 255.0F;
        float a = (color >> 24 & 0xFF) / 255.0F;

        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer worldRenderer = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        worldRenderer.begin(GL_QUADS, POSITION_COLOR);
        worldRenderer.pos(x1, y2, 0.0D).color(r, g, b, a).endVertex();
        worldRenderer.pos(x2, y2, 0.0D).color(r, g, b, a).endVertex();
        worldRenderer.pos(x2, y1, 0.0D).color(r, g, b, a).endVertex();
        worldRenderer.pos(x1, y1, 0.0D).color(r, g, b, a).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    // Bordered

    public static void rectangleBordered(double x1, double y1, double x2, double y2, int borderColor, int internalColor) {
        rectangleBordered(x1, y1, x2, y2, 0.5, borderColor, internalColor);
    }

    public static void rectangleBordered(double x1, double y1, double x2, double y2, double width, int borderColor, int internalColor) {
        rectangle(x1 + width, y1 + width, x2 - width, y2 - width, internalColor);
        rectangle(x1 + width, y1, x2 - width, y1 + width, borderColor);
        rectangle(x1, y1, x1 + width, y2, borderColor);
        rectangle(x2 - width, y1, x2, y2, borderColor);
        rectangle(x1 + width, y2 - width, x2 - width, y2, borderColor);
    }

    // Gradient

    public static void rectangleGradient(double x1, double y1, double x2, double y2, int c1, int c2) {
        rectangleGradient(x1, y1, x2, y2, new int[] { c1, c2 });
    }

    private static void rectangleGradient(double x1, double y1, double x2, double y2, int[] color) {
        float[] r = new float[color.length];
        float[] g = new float[color.length];
        float[] b = new float[color.length];
        float[] a = new float[color.length];
        for (int i = 0; i < color.length; i++) {
            r[i] = ((color[i] >> 16 & 0xFF) / 255.0F);
            g[i] = ((color[i] >> 8 & 0xFF) / 255.0F);
            b[i] = ((color[i] & 0xFF) / 255.0F);
            a[i] = ((color[i] >> 24 & 0xFF) / 255.0F);
        }
        GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.blendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.shadeModel(GL_FLAT);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer worldRenderer = tessellator.getBuffer();
        worldRenderer.begin(GL_QUADS, POSITION_COLOR);
        // Clean this up
        if (color.length == 1) {
            worldRenderer.pos(x2, y1, 0.0D).color(r[0], g[0], b[0], a[0]).endVertex();
            worldRenderer.pos(x1, y1, 0.0D).color(r[0], g[0], b[0], a[0]).endVertex();
            worldRenderer.pos(x1, y2, 0.0D).color(r[0], g[0], b[0], a[0]).endVertex();
            worldRenderer.pos(x2, y2, 0.0D).color(r[0], g[0], b[0], a[0]).endVertex();
        } else if (color.length == 2 || color.length == 3) {
            worldRenderer.pos(x2, y1, 0.0D).color(r[0], g[0], b[0], a[0]).endVertex();
            worldRenderer.pos(x1, y1, 0.0D).color(r[0], g[0], b[0], a[0]).endVertex();
            worldRenderer.pos(x1, y2, 0.0D).color(r[1], g[1], b[1], a[1]).endVertex();
            worldRenderer.pos(x2, y2, 0.0D).color(r[1], g[1], b[1], a[1]).endVertex();
        } else if (color.length >= 4) {
            worldRenderer.pos(x2, y1, 0.0D).color(r[0], g[0], b[0], a[0]).endVertex();
            worldRenderer.pos(x1, y1, 0.0D).color(r[1], g[1], b[1], a[1]).endVertex();
            worldRenderer.pos(x1, y2, 0.0D).color(r[2], g[2], b[2], a[2]).endVertex();
            worldRenderer.pos(x2, y2, 0.0D).color(r[3], g[3], b[3], a[3]).endVertex();
        }
        tessellator.draw();
        GlStateManager.shadeModel(GL_FLAT);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    // Bordered Gradient

    public static void rectangleBorderedGradient(double x1, double y1, double x2, double y2, int border, int c1, int c2) {
        rectangleBorderedGradient(x1, y1, x2, y2, border, c1, c2, 0.5);
    }

    public static void rectangleBorderedGradient(double x1, double y1, double x2, double y2, int border, int c1, int c2, double width) {
        rectangleBorderedGradient(x1, y1, x2, y2, new int[] { c1, c2 }, new int[] { border }, width);
    }

    public static void rectangleBorderedGradient(double x1, double y1, double x2, double y2, int[] fill, int[] outline, double width) {
        rectangleOutlinedGradient(x1, y1, x2, y2, outline, width);
        rectangleGradient(x1 + width, y1 + width, x2 - width, y2 - width, fill);
    }

    public static void rectangleOutlinedGradient(double x1, double y1, double x2, double y2, int[] color, double width) {
        rectangleGradient(x1, y1, x2, y1 + width, color);
        rectangleGradient(x1, y2 - width, x2, y2, color);
        rectangleGradient(x1, y1 + width, x1 + width, y2 - width, color);
        rectangleGradient(x2 - width, y1 + width, x2, y2 - width, color);
    }
}
