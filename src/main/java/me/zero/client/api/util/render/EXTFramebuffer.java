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

    private int fboID, textureID, bufferID;
    private final int textureWidth, textureHeight;

    public EXTFramebuffer(Framebuffer fbo) {
        this(fbo.framebufferWidth, fbo.framebufferHeight);
    }

    public EXTFramebuffer(int textureWidth, int textureHeight) {
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.createFBO();
    }

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

    public final int getFramebufferID() {
        return this.fboID;
    }

    public final int getTextureID() {
        return this.textureID;
    }

    public final int getRenderBufferID() {
        return this.bufferID;
    }

    public final int getFramebufferWidth() {
        return this.textureWidth;
    }

    public final int getFramebufferHeight() {
        return this.textureHeight;
    }

    public final void delete() {
        glDeleteRenderbuffersEXT(bufferID);
        glDeleteFramebuffersEXT(fboID);
        glDeleteTextures(textureID);
    }
}
