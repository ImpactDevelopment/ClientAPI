package me.zero.client.api.util.render.shader;

import me.zero.client.api.util.ReflectionUtils;
import me.zero.client.api.util.interfaces.Action;
import me.zero.client.api.util.interfaces.Helper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.Timer;
import org.lwjgl.opengl.GL11;

import java.lang.reflect.Method;

import static org.lwjgl.opengl.GL11.*;

/**
 * Tool for rendering objects to a shader framebuffer, then drawing
 * that framebuffer.
 *
 * @since 1.0
 *
 * Created by Brady on 2/17/2017.
 */
public class ShaderRender implements Helper, Action {

    private final ShaderProgram program;
    private final Framebuffer fbo;

    public ShaderRender(ShaderProgram program, Framebuffer fbo) {
        this.program = program;
        this.fbo = fbo;
    }

    @Override
    public final void start() {
        Method setupCameraTransform = ReflectionUtils.getMethod(mc.entityRenderer, "setupCameraTransform", float.class, int.class);
        ReflectionUtils.callMethod(
                mc.entityRenderer,
                setupCameraTransform,
                ((Timer) ReflectionUtils.getField(mc, "timer")).renderPartialTicks,
                0
        );

        RenderHelper.enableStandardItemLighting();
        fbo.bindFramebuffer(false);
        glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        glClear(GL_DEPTH_BUFFER_BIT | GL_COLOR_BUFFER_BIT);
    }

    @Override
    public final void stop() {
        mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        mc.entityRenderer.setupOverlayRendering();

        program.update(fbo.framebufferTexture);

        fbo.unbindFramebuffer();
        mc.getFramebuffer().bindFramebuffer(true);
        draw();
    }

    private void draw() {
        glPushMatrix();
        glColor4f(1, 1, 1, 1);
        GlStateManager.bindTexture(program.getFramebuffer().getTextureID());
        glBegin(GL_POLYGON);
        glTexCoord2d(0, 1);
        glVertex2d(0, 0);
        glTexCoord2d(0, 0);
        glVertex2d(0, mc.displayHeight / 2);
        glTexCoord2d(1, 0);
        glVertex2d(mc.displayWidth / 2, mc.displayHeight / 2);
        glTexCoord2d(1, 0);
        glVertex2d(mc.displayWidth / 2, mc.displayHeight / 2);
        glTexCoord2d(1, 1);
        glVertex2d(mc.displayWidth / 2, 0);
        glTexCoord2d(0, 1);
        glVertex2d(0, 0);
        glEnd();
        glPopMatrix();
    }
}
