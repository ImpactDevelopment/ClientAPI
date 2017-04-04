package me.zero.client.wrapper;

import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;

import java.util.List;

/**
 * Used to retrieve private lists in Shader Groups
 *
 * @since 1.0
 *
 * Created by Brady on 4/4/2017.
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
