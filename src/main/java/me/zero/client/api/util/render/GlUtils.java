package me.zero.client.api.util.render;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * OpenGL utils
 *
 * @since 1.0
 *
 * Created by Brady on 2/20/2017.
 */
public class GlUtils {

    /**
     * Credits to Halalaboos
     */
    public static int applyTexture(int texId, File file, int filter, int wrap) throws IOException {
        applyTexture(texId, ImageIO.read(file), filter, wrap);
        return texId;
    }

    /**
     * Credits to Halalaboos
     */
    public static int applyTexture(int texId, BufferedImage image, int filter, int wrap) {
        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }

        buffer.flip();
        applyTexture(texId, image.getWidth(), image.getHeight(), buffer, filter, wrap);
        return texId;
    }

    /**
     * Credits to Halalaboos
     */
    public static int applyTexture(int texId, int width, int height, ByteBuffer pixels, int filter, int wrap) {
        glBindTexture(GL_TEXTURE_2D, texId);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrap);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrap);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
        glBindTexture(GL_TEXTURE_2D, 0);
        return texId;
    }

    /**
     * Sets the color from a hex value
     *
     * @since 1.0
     *
     * @param hex The hex value
     */
    public static void glColor(int hex) {
        float[] color = getColor(hex);
        GlStateManager.color(color[0], color[1], color[2], color[3]);
    }

    /**
     * Parses the RGBA values from a hex value
     *
     * @since 1.0
     *
     * @param hex The hex value
     * @return The parsed RGBA array
     */
    public static float[] getColor(int hex) {
        return new float[] {
                (hex >> 16 & 255) / 255F,
                (hex >> 8 & 255) / 255F,
                (hex & 255) / 255F,
                (hex >> 24 & 255) / 255F
        };
    }

    /**
     * Parses the hex value from RGBA values
     *
     * @since 1.0
     *
     * @param color The RGBA array
     * @return The parsed hex value
     */
    public static int getColor(float[] color) {
        int r = (int) (color[0] * 255F);
        int g = (int) (color[1] * 255F);
        int b = (int) (color[2] * 255F);
        int a = (int) (color[3] * 255F);
        return ((a & 0xFF) << 24) |
                ((r & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                (b & 0xFF);
    }

    /**
     * Gets the Model View Matrix as a FloatBuffer
     *
     * @since 1.0
     *
     * @return The Model View Matrix
     */
    public static FloatBuffer getModelViewMatrix() {
        FloatBuffer modelview = BufferUtils.createFloatBuffer(16);
        glGetFloat(GL_MODELVIEW_MATRIX, modelview);
        return modelview;
    }

    /**
     * Gets the Projection Matrix as a FloatBuffer
     *
     * @since 1.0
     *
     * @return The Projection Matrix
     */
    public static FloatBuffer getProjectionMatrix() {
        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        glGetFloat(GL_PROJECTION_MATRIX, projection);
        return projection;
    }

    /**
     * Gets the Render Viewport as an IntegerBuffer
     *
     * @since 1.0
     *
     * @return The Viewport
     */
    public static IntBuffer getViewport() {
        IntBuffer viewport = BufferUtils.createIntBuffer(16);
        glGetInteger(GL_VIEWPORT, viewport);
        return viewport;
    }
}
