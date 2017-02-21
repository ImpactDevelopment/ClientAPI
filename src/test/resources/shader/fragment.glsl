#version 120

uniform sampler2D sampler;
uniform vec2 texel;
uniform int width;

void main() {
    vec4 centerCol = texture2D(sampler, gl_TexCoord[0].st);
    if(centerCol.a != 0.0F)
    {
        gl_FragColor = vec4(0, 0, 0, 0);
        return;
    }
    float closest = width * 1.0F;
    for(int xo = -width; xo <= width; xo++)
    {
        for(int yo = -width; yo <= width; yo++)
        {
            vec4 currCol = texture2D(sampler, gl_TexCoord[0].st + vec2(xo * texel.x, yo * texel.y));
            if(currCol.r != 0.0F || currCol.g != 0.0F || currCol.b != 0.0F || currCol.a != 0.0F)
            {
                float currentDist = sqrt(xo * xo + yo * yo);
                if(currentDist < closest)
                {
                    closest = currentDist;
                }
            }
        }
    }
    float m = 2.0;
    float fade = max(0, ((width * 1.0F) - (closest - 1)) / (width * 1.0F));
    gl_FragColor = vec4(m - fade, m - fade, m - fade, fade);
}