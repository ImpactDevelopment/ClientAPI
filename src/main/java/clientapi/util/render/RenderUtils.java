/*
 * Copyright 2017 ImpactDevelopment
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
import clientapi.util.render.gl.GlUtils;
import clientapi.util.render.gl.glenum.GLClientState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import pw.knx.feather.tessellate.Tessellator;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * The basic render utils for any client,
 *
 * @author Brady
 * @since 2/4/2017 12:00 PM
 */
public final class RenderUtils {

    private RenderUtils() {}

    /**
     * Instance of the Tessellator
     */
    public static final Tessellator tessellator = Tessellator.createExpanding(4, 1, 2);

    /**
     * Stores ClientState Gl Caps when setting up
     */
    private static final List<Integer> csBuffer = new ArrayList<>();

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
    public static void setupClientState(GLClientState state, boolean enabled) {
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
        glLineWidth(width);

        setupRender(true);
        setupClientState(GLClientState.VERTEX, true);
        tessellator.addVertex(x, y, z).addVertex(x1, y1, z1).draw(GL_LINE_STRIP);
        setupClientState(GLClientState.VERTEX, false);
        setupRender(false);
    }

    /**
     * Draws a flipped textured rectangle
     *
     * @param x1 Top corner X of the rectangle
     * @param y1 Top corner Y of the rectangle
     * @param x2 Bottom corner X of the rectangle
     * @param y2 Bottom corner Y of the rectangle
     */
    public static void drawFlippedTexturedRect(float x1, float y1, float x2, float y2) {
        setupClientState(GLClientState.TEXTURE, true);

        tessellator
                .addVertex(x1, y2, 0).setTexture(0, 0)
                .addVertex(x2, y2, 0).setTexture(1, 0)
                .addVertex(x2, y1, 0).setTexture(1, 1)
                .addVertex(x1, y1, 0).setTexture(0, 1)
                .draw(GL_QUADS);

        setupClientState(GLClientState.TEXTURE, false);
    }

    /**
     * Draws a reflected textured rectangle
     *
     * @param x1 Top corner X of the rectangle
     * @param y1 Top corner Y of the rectangle
     * @param x2 Bottom corner X of the rectangle
     * @param y2 Bottom corner Y of the rectangle
     */
    public static void drawReflectedTexturedRect(float x1, float y1, float x2, float y2) {
        setupClientState(GLClientState.TEXTURE, true);

        tessellator
                .addVertex(x1, y2, 0).setTexture(1, 0)
                .addVertex(x2, y2, 0).setTexture(1, 0)
                .addVertex(x2, y1, 0).setTexture(0, 1)
                .addVertex(x1, y1, 0).setTexture(1, 1)
                .draw(GL_QUADS);

        setupClientState(GLClientState.TEXTURE, false);
    }

    /**
     * Renders a rectangle
     *
     * @param x1 Top corner X of the rectangle
     * @param y1 Top corner Y of the rectangle
     * @param x2 Bottom corner X of the rectangle
     * @param y2 Bottom corner Y of the rectangle
     * @param color The color of the rectangle
     */
    public static void rectangle(float x1, float y1, float x2, float y2, int color) {
        GlUtils.glColor(color);

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
        setupClientState(GLClientState.VERTEX, true);
        tessellator.addVertex(x1, y2, 0).addVertex(x2, y2, 0).addVertex(x2, y1, 0).addVertex(x1, y1, 0).draw(GL_QUADS);
        setupClientState(GLClientState.VERTEX, false);
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
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.shadeModel(GL_FLAT);
        setupClientState(GLClientState.COLOR, true);

        tessellator
                .setColor(c1).addVertex(x1, y2, 0)
                .setColor(c2).addVertex(x2, y2, 0)
                .setColor(c3).addVertex(x2, y1, 0)
                .setColor(c4).addVertex(x1, y1, 0)
                .draw(GL_QUADS);

        setupClientState(GLClientState.COLOR, false);
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
