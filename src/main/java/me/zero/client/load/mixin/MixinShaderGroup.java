package me.zero.client.load.mixin;

import me.zero.client.wrapper.IShaderGroup;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

/**
 * @author Brady
 * @since 4/27/2017 12:00PM
 */
@Mixin(ShaderGroup.class)
public class MixinShaderGroup implements IShaderGroup {

    @Shadow @Final private List<Shader> listShaders;
    @Shadow @Final private List<Framebuffer> listFramebuffers;

    @Override
    public List<Shader> getShaders() {
        return this.listShaders;
    }

    @Override
    public List<Framebuffer> getFBOs() {
        return this.listFramebuffers;
    }
}
