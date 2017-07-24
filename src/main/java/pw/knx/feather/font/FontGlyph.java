package pw.knx.feather.font;

import pw.knx.feather.texture.Texture;

/**
 * Identifies a single glyph in the laid-out string. Includes a reference to a Texture Object with the OpenGL texture ID
 * and position of the pre-rendered glyph image, and includes the x/y pixel coordinates of where this glyph occurs within
 * the string to which this Glyph object belongs.
 *
 * @author KNOXDEV
 * @since 6/8/2017 5:35 PM
 */
public class FontGlyph implements Comparable<FontGlyph> {

	/**
	 * Glyph's horizontal/vertical position (in pixels) relative to the entire string's baseline
	 */
	public final int x, y;

	/**
	 * Texture ID and position/size of the glyph's pre-rendered image within the cache texture.
	 */
	public final Texture texture;

	/**
	 * Your standard constructor. See class documentation for details.
	 *
	 * @param texture Texture ID and position/size of the glyph's pre-rendered image within the cache texture
	 * @param x       Glyph's horizontal position (in pixels) relative to the entire string's baseline
	 * @param y       Glyph's vertical position (in pixels) relative to the entire string's baseline
	 */
	FontGlyph(Texture texture, int x, int y) {
		this.texture = texture;
		this.x = x;
		this.y = y;
	}

	/**
	 * Allows arrays of Glyph objects to be sorted. Performs numeric comparison on texture ID.
	 *
	 * @param o the other Glyph object being compared with this one
	 * @return either -1, 0, or 1 if this < other, this == other, or this > other
	 */
	@Override
	public int compareTo(FontGlyph o) {
		return (this.texture.id() == o.texture.id()) ? 0 : 1;
	}
}
