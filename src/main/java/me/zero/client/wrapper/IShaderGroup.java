package me.zero.client.wrapper;

import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;

import java.util.List;

/**
 * Used to retrieve private lists in Shader Groups
 *
 * @since 1.0
 *
 * @author Brady
 * @since 4/4/2017 12:00PM
 */
public interface IShaderGroup {

    /**
     * @since 1.0
     *
     * @return List of ShaderGroup shaders
     */
    List<Shader> getShaders();

    /**
     * @since 1.0
     *
     * @return List of ShaderGroup FBOs
     */
    List<Framebuffer> getFBOs();
}
