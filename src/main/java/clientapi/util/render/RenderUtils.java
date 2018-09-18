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

package clientapi.util.render;

import clientapi.util.math.Vec2;
import clientapi.util.math.Vec3;
import clientapi.util.render.gl.GLUtils;
import clientapi.util.render.gl.glenum.GLClientState;
import net.minecraft.client.renderer.GlStateManager;
import pw.knx.feather.tessellate.Tessellator;

import java.util.Stack;

import static org.lwjgl.opengl.GL11.*;

/**
 * The basic render utils for any client
 *
 * @author Brady
 * @since 2/4/2017
 */
public final class RenderUtils {

    private RenderUtils() {}

    /**
     * Instance of the Tessellator
     */
    public static final Tessellator tessellator = Tessellator.createExpanding(4, 1, 2);

    /**
     * The current OpenGL Client State stack
     */
    private static final Stack<GLClientState[]> clientStateStack = new Stack<>();

    /**
     * Called before rendering. Enables blending,
     * line smoothing, disables 2D texturing,
     * depth and the depth mask.
     *
     * @param start true if we are starting to render, false if we are finishing
     */
    public static void setupRender(boolean start) {
        if (start) {
            GlStateManager.enableBlend();
            glEnable(GL_LINE_SMOOTH);
            GlStateManager.disableDepth();
            GlStateManager.disableTexture2D();

            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
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
     * Pushes a set of client states onto the stack, and enabled them.
     *
     * @param states The client states to enable
     */
    public static void pushClientState(GLClientState... states) {
        clientStateStack.push(states);
        for (GLClientState state : states)
            GlStateManager.glEnableClientState(state.getCap());
    }

    /**
     * Pops the client state stack, disabling whatever was on it.
     */
    public static void popClientState() {
        for (GLClientState state : clientStateStack.pop())
            GlStateManager.glDisableClientState(state.getCap());
    }

    /**
     * Draws a line from one Vec2 to another
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
     * @param x Start X
     * @param y Start Y
     * @param z Start X
     * @param x1 End X
     * @param y1 End Y
     * @param z1 End Z
     * @param width Line width
     */
    public static void drawLine(float x, float y, float z, float x1, float y1, float z1, float width) {
        GlStateManager.glLineWidth(width);

        setupRender(true);
        pushClientState(GLClientState.VERTEX);

        tessellator
                .addVertex(x, y, z)
                .addVertex(x1, y1, z1)
                .draw(GL_LINE_STRIP);

        popClientState();
        setupRender(false);
    }

    /**
     * Draws a flipped textured rectangle
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     */
    public static void drawFlippedTexturedRect(float x1, float y1, float x2, float y2) {
        pushClientState(GLClientState.VERTEX, GLClientState.TEXTURE);

        tessellator
                .setTexture(0, 0).addVertex(x1, y2, 0)
                .setTexture(1, 0).addVertex(x2, y2, 0)
                .setTexture(1, 1).addVertex(x2, y1, 0)
                .setTexture(0, 1).addVertex(x1, y1, 0)
                .draw(GL_QUADS);

        popClientState();
    }

    /**
     * Draws a reflected textured rectangle
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     */
    public static void drawReflectedTexturedRect(float x1, float y1, float x2, float y2) {
        pushClientState(GLClientState.VERTEX, GLClientState.TEXTURE);

        tessellator
                .setTexture(1, 0).addVertex(x1, y2, 0)
                .setTexture(0, 0).addVertex(x2, y2, 0)
                .setTexture(0, 1).addVertex(x2, y1, 0)
                .setTexture(1, 1).addVertex(x1, y1, 0)
                .draw(GL_QUADS);

        popClientState();
    }

    /**
     * Renders a rectangle at the specified position with the specified color.
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     * @param color The color of the rectangle
     */
    public static void rectangle(float x1, float y1, float x2, float y2, int color) {
        GLUtils.glColor(color);

        if (x1 > x2) {
            float temp = x1;
            x1 = x2;
            x2 = temp;
        }

        if (y1 > y2) {
            float temp = y1;
            y1 = y2;
            y2 = temp;
        }

        setupRender(true);
        pushClientState(GLClientState.VERTEX, GLClientState.COLOR);

        tessellator
                .setColor(color).addVertex(x1, y2, 0)
                .setColor(color).addVertex(x2, y2, 0)
                .setColor(color).addVertex(x2, y1, 0)
                .setColor(color).addVertex(x1, y1, 0)
                .draw(GL_QUADS);

        popClientState();
        setupRender(false);
    }

    /**
     * Renders a colored rectangle at the specified position with the specified
     * inner color, and border color. The border is given a width of 0.5
     *
     * @see RenderUtils#rectangleBordered(float, float, float, float, float, int, int)
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     * @param borderColor The outer color of the rectangle
     * @param internalColor The inner color of the rectangle
     */
    public static void rectangleBordered(float x1, float y1, float x2, float y2, int borderColor, int internalColor) {
        rectangleBordered(x1, y1, x2, y2, 0.5F, borderColor, internalColor);
    }

    /**
     * Renders a colored rectangle at the specified position with the specified
     * inner color, border color, and border width.
     *
     * @see RenderUtils#rectangleBordered(float, float, float, float, int, int)
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     * @param width The width of the border
     * @param borderColor The outer color of the rectangle
     * @param internalColor The inner color of the rectangle
     */
    public static void rectangleBordered(float x1, float y1, float x2, float y2, float width, int borderColor, int internalColor) {
        rectangle(x1 + width, y1 + width, x2 - width, y2 - width, internalColor);
        rectangle(x1 + width, y1, x2 - width, y1 + width, borderColor);
        rectangle(x1, y1, x1 + width, y2, borderColor);
        rectangle(x2 - width, y1, x2, y2, borderColor);
        rectangle(x1 + width, y2 - width, x2 - width, y2, borderColor);
    }

    /**
     * Renders a rectangle with the specified corner coordinates with a
     * gradient that starts at the top of the rectangle and ends at the bottom.
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     * @param c1 The color at the top of the rectangle
     * @param c2 The color at the bottom of the rectangle
     */
    public static void rectangleGradient(float x1, float y1, float x2, float y2, int c1, int c2) {
        rectangleGradient(x1, y1, x2, y2, new int[] { c1, c2 });
    }

    /**
     * Renders a rectangle with the specified corner coordinates with a gradient
     * from the specified array of colors. Colors are oriented clockwise starting
     * at the top left corner of the rectangle.
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     * @param color The colors for each of the rectangle verticies clockwise
     * @throws IllegalArgumentException if the length of the color array is equal to 0
     */
    public static void rectangleGradient(float x1, float y1, float x2, float y2, int[] color) {
        if (color.length == 0)
            throw new IllegalArgumentException("At least one set of colors should be supplied");

        int c1, c2, c3, c4;

        if (color.length == 1) {
            c1 = color[0];
            c2 = color[0];
            c3 = color[0];
            c4 = color[0];
        } else if (color.length < 4) {
            c1 = color[0];
            c2 = color[0];
            c3 = color[1];
            c4 = color[1];
        } else {
            c1 = color[0];
            c2 = color[1];
            c3 = color[2];
            c4 = color[3];
        }

        if (x1 > x2) {
            float temp = x1;
            x1 = x2;
            x2 = temp;
        }

        if (y1 > y2) {
            float temp = y1;
            y1 = y2;
            y2 = temp;
        }

        setupRender(true);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(GL_FLAT);
        pushClientState(GLClientState.VERTEX, GLClientState.COLOR);

        tessellator
                .setColor(c1).addVertex(x1, y2, 0)
                .setColor(c2).addVertex(x2, y2, 0)
                .setColor(c3).addVertex(x2, y1, 0)
                .setColor(c4).addVertex(x1, y1, 0)
                .draw(GL_QUADS);

        popClientState();
        setupRender(false);
    }

    /**
     * Renders a rectangle with the specified corner coordinates with a
     * gradient that starts at the top of the rectangle and ends at the bottom
     * and a border with a default width of 0.5 and specified color.
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     * @param border The border color of the rectangle
     * @param c1 The color at the top of the rectangle
     * @param c2 The color at the bottom of the rectangle
     */
    public static void rectangleBorderedGradient(float x1, float y1, float x2, float y2, int border, int c1, int c2) {
        rectangleBorderedGradient(x1, y1, x2, y2, border, c1, c2, 0.5F);
    }

    /**
     * Renders a rectangle with the specified corner coordinates with a
     * gradient that starts at the top of the rectangle and ends at the bottom
     * and a border with a specified width and specified color.
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     * @param border The border color of the rectangle
     * @param c1 The color at the top of the rectangle
     * @param c2 The color at the bottom of the rectangle
     * @param width The width of the border
     */
    public static void rectangleBorderedGradient(float x1, float y1, float x2, float y2, int border, int c1, int c2, float width) {
        rectangleBorderedGradient(x1, y1, x2, y2, new int[] { c1, c2 }, new int[] { border }, width);
    }

    /**
     * Renders a rectangle with the specified corner coordinates with a
     * gradient that starts at the top of the rectangle and ends at the bottom
     * and a border with a specified width and specified color. Fill and border
     * colors are oriented clockwise starting at the top left corner.
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     * @param fill The fill vertex colors oriented clockwise
     * @param border The border vertex colors oriented clockwise
     * @param width The width of the border
     */
    public static void rectangleBorderedGradient(float x1, float y1, float x2, float y2, int[] fill, int[] border, float width) {
        rectangleOutlinedGradient(x1, y1, x2, y2, border, width);
        rectangleGradient(x1 + width, y1 + width, x2 - width, y2 - width, fill);
    }

    /**
     * Renders a rectangle border that is colored with the specified gradient
     * colors and has the specified border width. Colors are oriented clockwise
     * starting at the top left corner of the rectangle border.
     *
     * @param x1 Top left corner X of the rectangle
     * @param y1 Top left corner Y of the rectangle
     * @param x2 Bottom right corner X of the rectangle
     * @param y2 Bottom right corner Y of the rectangle
     * @param color The vertex colors oriented clockwise
     * @param width The width of the border
     */
    public static void rectangleOutlinedGradient(float x1, float y1, float x2, float y2, int[] color, float width) {
        rectangleGradient(x1, y1, x2, y1 + width, color);
        rectangleGradient(x1, y2 - width, x2, y2, color);
        rectangleGradient(x1, y1 + width, x1 + width, y2 - width, color);
        rectangleGradient(x2 - width, y1 + width, x2, y2 - width, color);
    }
}
