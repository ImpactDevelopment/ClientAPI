package me.zero.client.load.mixin.wrapper;

import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

/**
 * @author Brady
 * @since 4/4/2017 12:00 PM
 */
@Mixin(ShaderGroup.class)
public interface IShaderGroup {

    /**
     * @return List of ShaderGroup shaders
     */
    @Accessor List<Shader> getListShaders();

    /**
     * @return List of ShaderGroup FBOs
     */
    @Accessor List<Framebuffer> getListFramebuffers();
}
