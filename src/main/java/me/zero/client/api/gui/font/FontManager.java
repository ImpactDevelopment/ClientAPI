package me.zero.client.api.gui.font;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Manage fonts and their textures. <br/>
 * Created by Brandon Williams on 2/12/2017.
 */
public class FontManager {

    private final Map<Font, FontData> fonts = new HashMap<>();

    /**
     * @return A FontData object which will either be retrieved or instantiated.
     * */
    public FontData getFont(Font font, boolean antialias) {
        // Filter by the name, size, and style. Crappy way of doing so, but it gets the job done.
        // I am unsure if .equals() checks this information, but I believe it doesn't.
        Optional<Map.Entry<Font, FontData>> optional = fonts.entrySet().stream().filter(entry -> entry.getKey().getName().equals(font.getName()) && entry.getKey().getSize() == font.getSize() && entry.getKey().getStyle() == font.getStyle()).findFirst();
        if (optional.isPresent())
            return optional.get().getValue();
        // Instantiate.
        FontData fontData = new FontData();
        fontData.setFont(font, antialias);
        fonts.put(font, fontData);
        return fontData;
    }

    /**
     * @return a FontData object which will either be retrieved or instantiated.
     * */
    public FontData getFont(String name, int size, int style, boolean antialias) {
        return getFont(new Font(name, style, size), antialias);
    }

    public Map<Font, FontData> getFonts() {
        return fonts;
    }
}
