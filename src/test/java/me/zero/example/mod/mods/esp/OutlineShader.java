package me.zero.example.mod.mods.esp;

import me.zero.client.api.util.math.Vec2;
import me.zero.client.api.util.render.shader.ShaderProgram;
import me.zero.client.api.util.render.shader.UniformVariable;
import net.minecraft.client.shader.Framebuffer;

/**
 * Created by Brady on 2/18/2017.
 */
public class OutlineShader extends ShaderProgram {

    public OutlineShader(Framebuffer fbo) {
        super("/shader/vertex.glsl", "/shader/fragment.glsl", fbo);
    }

    @Override
    public void update() {
        UniformVariable sampler = getUniform("sampler");
        UniformVariable texel = getUniform("texel");
        UniformVariable width = getUniform("width");

        float texelSize = 2F;

        sampler.setInt(0);
        texel.setVec(new Vec2(1.0F / this.framebuffer.getFramebufferWidth() * texelSize, 1.0F / this.framebuffer.getFramebufferHeight() * texelSize));
        width.setInt(1);
    }
}
