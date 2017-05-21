package me.zero.client.api.util.render.shader;

import me.zero.client.api.util.io.StreamReader;
import me.zero.client.api.util.render.shader.adapter.ShaderAdapter;
import me.zero.client.api.util.render.shader.glenum.GlShaderStatus;
import me.zero.client.api.util.render.shader.glenum.GlShaderType;

/**
 * Utils for Shader Programs
 *
 * @author Brady
 * @since 2/16/2017 12:00 PM
 */
final class ShaderHelper {

    private ShaderHelper() {}

    /**
     * Loads a shader of the specified type from the specified path
     *
     * @param path Shader path
     * @param type Shader type
     * @return The Shader's Object ID
     */
    static int loadShader(ShaderAdapter adapter, String path, GlShaderType type) {
        int shaderID = adapter.createShader(type);
        if (shaderID == 0)
            return 0;

        String src = new StreamReader(Shader.class.getResourceAsStream(path)).read();
        adapter.shaderSource(shaderID, src);
        adapter.compileShader(shaderID);
        adapter.checkStatus(shaderID, GlShaderStatus.COMPILE);
        return shaderID;
    }
}
