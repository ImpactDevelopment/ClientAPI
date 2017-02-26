package me.zero.client.api.util.render;

import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.math.Vec3;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;

import static net.minecraft.client.renderer.vertex.DefaultVertexFormats.POSITION_TEX;
import static org.lwjgl.opengl.GL11.*;

/**
 * The basic render utils for any client
 *
 * @since 1.0
 *
 * Created by Brady on 2/4/2017.
 */
public class RenderUtils {

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
        renderer.begin(GL_TRIANGLES, POSITION_TEX);
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
        preRender();
        glLineWidth(width);
        renderer.begin(GL_TRIANGLES, POSITION_TEX);
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
}
