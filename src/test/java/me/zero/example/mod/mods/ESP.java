package me.zero.example.mod.mods;

import me.zero.client.api.event.EventHandler;
import me.zero.client.api.event.Listener;
import me.zero.client.api.event.defaults.GlintEffectEvent;
import me.zero.client.api.event.defaults.Render2DEvent;
import me.zero.client.api.event.type.EventPriority;
import me.zero.client.api.module.Mod;
import me.zero.client.api.module.Module;
import me.zero.client.api.util.math.Vec3;
import me.zero.client.api.util.render.shader.ShaderRender;
import me.zero.client.api.wrapper.IEntity;
import me.zero.client.api.wrapper.IMinecraft;
import me.zero.client.api.wrapper.IRenderManager;
import me.zero.example.mod.mods.esp.OutlineShader;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

/**
 * Created by Brady on 2/18/2017.
 */
@Mod(name = "ESP", description = "Reveals entity location", bind = Keyboard.KEY_Y)
public class ESP extends Module {

    private final FloatBuffer buffer = BufferUtils.createFloatBuffer(4);

    private ShaderRender shader;
    private OutlineShader program;
    private Framebuffer fbo;
    private boolean renderGlint = true;

    @EventHandler
    private Listener<Render2DEvent> render2DListener = new Listener<>(event -> {
        renderGlint = false;

        checkFBO();

        float partialTicks = ((IMinecraft) mc).getTimer().renderPartialTicks;

        IRenderManager renderManager = (IRenderManager) mc.getRenderManager();
        Vec3 view = new Vec3(renderManager.getRenderPosX(), renderManager.getRenderPosY(), renderManager.getRenderPosZ());

        glPushMatrix();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        shader.start();
        mc.world.loadedEntityList.stream().filter(e -> (e instanceof EntityLivingBase || e instanceof EntityItem) && !e.isDead && e != mc.player).forEach(e -> {
            mc.entityRenderer.disableLightmap();
            RenderHelper.disableStandardItemLighting();

            glPushMatrix();
            Render<Entity> entityRender = mc.getRenderManager().getEntityRenderObject(e);
            if (entityRender != null) {
                Vec3 pos = ((IEntity) e).interpolate(partialTicks);
                glTranslated(pos.getX() - view.getX(), pos.getY() - view.getY(), pos.getZ() - view.getZ());

                setupColor(0xFF6000C8);
                entityRender.doRender(e, 0, 0, 0, 0, partialTicks);
                disableColor();
            }
            glPopMatrix();
        });
        shader.stop();

        glDisable(GL_BLEND);
        glPopMatrix();
        glColor4f(1, 1, 1, 1);

        renderGlint = true;
    }, EventPriority.LOWEST);

    @EventHandler
    private Listener<GlintEffectEvent> glintEffectListener = new Listener<>(event -> {
        if (!renderGlint) event.setCancelled(true);
    });

    private void setupColor(int color) {
        buffer.clear();
        buffer.put(0, (float)(color >> 16 & 255) / 255.0F);
        buffer.put(1, (float)(color >> 8 & 255) / 255.0F);
        buffer.put(2, (float)(color >> 0 & 255) / 255.0F);
        buffer.put(3, (float)(color >> 24 & 255) / 255.0F);
        glTexEnv(GL_TEXTURE_ENV, GL_TEXTURE_ENV_COLOR, buffer);
        glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_COMBINE);
        glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB,      GL_REPLACE);
        glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB,      GL_CONSTANT);
        glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB,     GL_SRC_COLOR);
        glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA,    GL_REPLACE);
        glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA,    GL_TEXTURE);
        glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_ALPHA,   GL_SRC_ALPHA);
    }

    private void disableColor() {
        glTexEnvi(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
        glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_RGB,      GL_MODULATE);
        glTexEnvi(GL_TEXTURE_ENV, GL_COMBINE_ALPHA,    GL_MODULATE);
        glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_RGB,      GL_TEXTURE);
        glTexEnvi(GL_TEXTURE_ENV, GL_SOURCE0_ALPHA,    GL_TEXTURE);
        glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_RGB,     GL_SRC_COLOR);
        glTexEnvi(GL_TEXTURE_ENV, GL_OPERAND0_ALPHA,   GL_SRC_ALPHA);
    }

    @Override
    public void onDisable() {
        if (fbo != null)
            fbo.unbindFramebuffer();
        fbo = null;

        if (program != null)
            program.delete();
        program = null;

        shader = null;
    }

    private void checkFBO() {
        if (fbo != null)
            fbo.framebufferClear();

        if (fbo == null || shader == null || program == null ||
                mc.displayWidth != fbo.framebufferWidth || mc.displayHeight != fbo.framebufferHeight) {
            if (fbo != null)
                fbo.unbindFramebuffer();
            fbo = new Framebuffer(mc.displayWidth, mc.displayHeight, false);
            if (program != null)
                program.delete();
            program = new OutlineShader(fbo);
            shader = new ShaderRender(program, fbo);
        }
    }
}
