#version 120

uniform sampler2D sampler;
uniform vec2 texel;
uniform int width;

void main() {
    float closest = width;
	vec4 color = vec4(0, 0, 0, 0);

	if (texture2D(sampler, gl_TexCoord[0].xy).a == 0.0F) {
		for (int x = -width; x <= width; x++) {
			for (int y = -width; y <= width; y++) {
				vec4 currCol = texture2D(sampler, gl_TexCoord[0].xy + vec2(x * texel.x, y * texel.y));
				if (currCol.a != 0.0F) {
					float dist = sqrt(x * x + y * y) / (width * 2);
					if (closest > dist) {
						color = currCol;
					}
				}
			}
		}
	}

	gl_FragColor = color;
}