package me.zero.client.api.gui.font.retriever;

import me.zero.client.api.gui.font.FontRetriever;

import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Brady on 2/11/2017.
 */
public class OnlineFontRetriever implements FontRetriever {

    /**
     * The font name on DaFont
     */
    private String url;

    public OnlineFontRetriever(String url) {
        this.url = url;
    }

    @Override
    public Font get(int style, float size) {
        try {
            return get(new URL(url).openStream(), style, size);
        } catch (IOException e) {
            return null;
        }
    }
}
