package me.zero.client.api.util.render;

import static org.lwjgl.opengl.GL11.*;

public enum GlClientState {

    VERTEX(GL_VERTEX_ARRAY),
    COLOR(GL_COLOR_ARRAY),
    TEXTURE(GL_TEXTURE_COORD_ARRAY);

    public final int cap;

    GlClientState(int cap) {
        this.cap = cap;
    }
}
