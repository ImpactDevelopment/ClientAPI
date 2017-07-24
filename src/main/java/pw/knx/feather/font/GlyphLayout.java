package pw.knx.feather.font;

/**
 *
 * This entry holds the laid-out glyph positions for the cached string along with some relevant metadata.
 *
 * @author KNOXDEV
 * @since 6/8/2017 5:34 PM
 */
public class GlyphLayout {

	/**
	 * Array of fully laid-out glyphs for the string. Sorted by logical order of characters (i.e. glyph.stringIndex)
	 */
	public final FontGlyph[] glyphs;

	/**
	 * The total horizontal advance (i.e. width) for this string in pixels.
	 */
	public final int width;

	GlyphLayout(FontGlyph[] glyphs, int width) {
		this.glyphs = glyphs;
		this.width = width;
	}
}
