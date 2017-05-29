package me.zero.client.load.mixin.wrapper;

import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;

import java.util.List;

/**
 * Used to retrieve private lists in Shader Groups
 *
 * @author Brady
 * @since 4/4/2017 12:00 PM
 */
public interface IShaderGroup {

    /**
     * @return List of ShaderGroup shaders
     */
    List<Shader> getShaders();

    /**
     * @return List of ShaderGroup FBOs
     */
    List<Framebuffer> getFBOs();
}
