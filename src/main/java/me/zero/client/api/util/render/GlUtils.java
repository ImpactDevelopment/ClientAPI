package me.zero.client.api.util.render;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

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

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
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

    public static void glColor(int hex) {
        float[] color = getColor(hex);
        GlStateManager.color(color[0], color[1], color[2], color[3]);
    }

    public static float[] getColor(int hex) {
        return new float[] {
                (hex >> 16 & 255) / 255F,
                (hex >> 8 & 255) / 255F,
                (hex & 255) / 255F,
                (hex >> 24 & 255) / 255F
        };
    }
}
