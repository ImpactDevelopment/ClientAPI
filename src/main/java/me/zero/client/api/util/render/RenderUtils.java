package me.zero.client.api.util.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

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
    private static VertexBuffer vertexBuffer = tessellator.getBuffer();

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
        vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(x, y1, 0).tex(0, 0).endVertex();
        vertexBuffer.pos(x1, y1, 0).tex(1, 0).endVertex();
        vertexBuffer.pos(x1, y, 0).tex(1, 1).endVertex();
        vertexBuffer.pos(x, y, 0).tex(0, 1).endVertex();
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
        vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(x, y1, 0).tex(1, 0).endVertex();
        vertexBuffer.pos(x1, y1, 0).tex(0, 0).endVertex();
        vertexBuffer.pos(x1, y, 0).tex(0, 1).endVertex();
        vertexBuffer.pos(x, y, 0).tex(1, 1).endVertex();
        tessellator.draw();
    }
}
