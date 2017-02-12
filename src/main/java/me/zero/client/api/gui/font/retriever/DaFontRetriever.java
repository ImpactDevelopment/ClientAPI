package me.zero.client.api.gui.font.retriever;

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
public class DaFontRetriever extends OnlineFontRetriever {

    public DaFontRetriever(String font) {
        super("http://dl.dafont.com/dl/?f=" + font);
    }
}
