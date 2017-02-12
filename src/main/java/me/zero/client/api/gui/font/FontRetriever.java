package me.zero.client.api.gui.font;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Retrieves a font
 *
 * @since 1.0
 *
 * Created by Brady on 2/11/2017.
 */
public interface FontRetriever {

    /**
     * Gets a font with the Specified Style and Size
     *
     * @since 1.0
     *
     * @param style The style of the font
     * @param size The font size
     * @return The font retrieved
     */
    Font get(int style, float size);

    /**
     * Gets a font from an {@code InputStream}
     * and the font style and size
     *
     * @since 1.0
     *
     * @param stream The Inputstream
     */
    default Font get(InputStream stream, int style, float size) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(style, size);
        } catch (IOException | FontFormatException e) {
            return null;
        }
    }
}
