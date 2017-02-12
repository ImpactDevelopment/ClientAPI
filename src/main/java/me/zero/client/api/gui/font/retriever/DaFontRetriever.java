package me.zero.client.api.gui.font.retriever;

import me.zero.client.api.gui.font.FontRetriever;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Gets a Font from DaFont. The "font" string
 * that is specified in the constructor is in
 * the URL of the font.
 *
 * http://www.dafont.com/FONTNAME.font
 *
 * @since 1.0
 *
 * Created by Brady on 2/11/2017.
 */
public class DaFontRetriever implements FontRetriever {

    /**
     * The Font URL
     */
    private static final String URL = "http://dl.dafont.com/dl/?f=";

    /**
     * The font name on DaFont
     */
    private String font;

    public DaFontRetriever(String font) {
        this.font = font;
    }

    @Override
    public Font get(int style, float size) {
        try {
            return get(new URL(URL + font).openStream(), style, size);
        } catch (IOException e) {
            return null;
        }
    }
}
