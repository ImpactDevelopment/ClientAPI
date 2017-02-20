package me.zero.client.api.util.render;

import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.client.shader.Framebuffer;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.EXTFramebufferObject.*;
import static org.lwjgl.opengl.EXTPackedDepthStencil.GL_DEPTH_STENCIL_EXT;
import static org.lwjgl.opengl.GL11.*;

/**
 * OpenGL EXT Framebuffer
 *
 * @since 1.0
 *
 * Created by Brady on 2/18/2017.
 */
public class EXTFramebuffer implements Helper {

    /**
     * Object IDs
     */
    private int fboID, textureID, bufferID;

    /**
     * Width and Height of this EXT FBO
     */
    private final int textureWidth, textureHeight;

    public EXTFramebuffer(Framebuffer fbo) {
        this(fbo.framebufferWidth, fbo.framebufferHeight);
    }

    public EXTFramebuffer(int textureWidth, int textureHeight) {
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.createFBO();
    }

    /**
     * Creates the FBO
     *
     * @since 1.0
     */
    private void createFBO() {
        checkSetupFBO();

        this.fboID = glGenFramebuffersEXT();
        this.textureID = glGenTextures();
        this.bufferID = glGenRenderbuffersEXT();

        glBindTexture(GL_TEXTURE_2D, this.textureID);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, 10496);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, 10496);
        glBindTexture(GL_TEXTURE_2D, 0);

        glBindTexture(GL_TEXTURE_2D, this.textureID);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, this.textureWidth, this.textureHeight, 0, GL_RGBA, GL_UNSIGNED_BYTE, (ByteBuffer) null);
        glBindFramebufferEXT(GL_FRAMEBUFFER_EXT, this.fboID);
        glFramebufferTexture2DEXT(GL_FRAMEBUFFER_EXT, GL_COLOR_ATTACHMENT0_EXT, GL_TEXTURE_2D, this.textureID, 0);

        glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, this.bufferID);
        glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL_DEPTH_STENCIL_EXT, this.textureWidth, this.textureHeight);
        glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT, GL_STENCIL_ATTACHMENT_EXT, GL_RENDERBUFFER_EXT, this.bufferID);
    }

    /**
     * Runs FBO Checks, ensures that stencil buffers
     * are enabled.
     *
     * @since 1.0
     */
    private static void checkSetupFBO() {
        Framebuffer fbo = mc.getFramebuffer();

        if (fbo.depthBuffer > -1) {
            glDeleteRenderbuffersEXT(fbo.depthBuffer);
            int stencil_depth_buffer_ID = glGenRenderbuffersEXT();
            glBindRenderbufferEXT(GL_RENDERBUFFER_EXT, stencil_depth_buffer_ID);
            glRenderbufferStorageEXT(GL_RENDERBUFFER_EXT, GL_DEPTH_STENCIL_EXT, mc.displayWidth, mc.displayHeight);
            glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT, GL_STENCIL_ATTACHMENT_EXT, GL_RENDERBUFFER_EXT, stencil_depth_buffer_ID);
            glFramebufferRenderbufferEXT(GL_FRAMEBUFFER_EXT, GL_DEPTH_ATTACHMENT_EXT, GL_RENDERBUFFER_EXT, stencil_depth_buffer_ID);
            fbo.depthBuffer = -1;
        }
    }

    /**
     * @since 1.0
     *
     * @return This Framebuffer's Object ID
     */
    public final int getFramebufferID() {
        return this.fboID;
    }

    /**
     * @since 1.0
     *
     * @return This Framebuffer's Texture ID
     */
    public final int getTextureID() {
        return this.textureID;
    }

    /**
     * @since 1.0
     *
     * @return This RenderBuffer's Object ID
     */
    public final int getRenderBufferID() {
        return this.bufferID;
    }

    /**
     * @since 1.0
     *
     * @return The width of the FBO
     */
    public final int getFramebufferWidth() {
        return this.textureWidth;
    }

    /**
     * @since 1.0
     *
     * @return The height of the FBO
     */
    public final int getFramebufferHeight() {
        return this.textureHeight;
    }

    /**
     * Deletes the FBO
     *
     * @since 1.0
     */
    public final void delete() {
        glDeleteRenderbuffersEXT(bufferID);
        glDeleteFramebuffersEXT(fboID);
        glDeleteTextures(textureID);
    }
}
