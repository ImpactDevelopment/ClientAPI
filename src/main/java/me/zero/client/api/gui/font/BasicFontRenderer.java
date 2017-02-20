package me.zero.client.api.gui.font;

import me.zero.client.api.util.render.GlUtils;
import me.zero.client.api.util.render.RenderUtils;
import net.minecraft.client.renderer.GlStateManager;

/**
 * Basic implementation of the FontRenderer interface.
 * */
public class BasicFontRenderer implements FontRenderer {

    protected int kerning = 0;

    protected FontData fontData = new FontData();

    public BasicFontRenderer() {}

	@Override
	public int drawString(FontData fontData, String text, int x, int y, int color) {
        if (!fontData.hasFont())
            return 0;
        GlStateManager.pushMatrix();
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        x *= 2;
        y *= 2;
        GlStateManager.enableBlend();
		fontData.bind();
		GlUtils.glColor(color);
		int size = text.length();
		for (int i = 0; i < size; i++) {
			char character = text.charAt(i);
			if (fontData.hasBounds(character)) {
                FontData.CharacterData area = fontData.getCharacterBounds(character);
                RenderUtils.drawTextureRect(x, y, area.width, area.height,
                        (float) area.x / fontData.getTextureWidth(),
                        (float) area.y / fontData.getTextureHeight(),
                        (float) (area.x + area.width) / fontData.getTextureWidth(),
                        (float) (area.y + area.height) / fontData.getTextureHeight());
				x += (area.width + kerning);
			}
		}
		GlStateManager.popMatrix();
		return x;
	}

	@Override
	public int drawString(String text, int x, int y, int color) {
        return drawString(fontData, text, x, y, color);
    }

    public int getKerning() {
        return kerning;
    }

    public void setKerning(int kerning) {
        this.kerning = kerning;
    }

    @Override
    public FontData getFontData() {
        return fontData;
    }

    @Override
    public void setFontData(FontData fontData) {
        this.fontData = fontData;
    }
}
