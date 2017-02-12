package me.zero.client.api.gui.font.retriever;

import me.zero.client.api.gui.font.FontRetriever;

import java.awt.*;

/**
 * Gets a font from it's package location.
 *
 * @since 1.0
 *
 * Created by Brady on 2/11/2017.
 */
public class PackagedFontRetriever implements FontRetriever {

    /**
     * The font name on DaFont
     */
    private String path;

    public PackagedFontRetriever(String path) {
        this.path = path;
    }

    @Override
    public Font get(int style, float size) {
        return get(PackagedFontRetriever.class.getResourceAsStream(path), style, size);
    }
}
